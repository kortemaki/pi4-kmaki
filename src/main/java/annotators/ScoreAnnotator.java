/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package annotators;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import type.NgramAnnotation;
import type.NgramSet;
import type.PassageScoring;
import type.ScoredSpan;
import type.TokenizedSpan;

/**
 * A simple scoring annotator for PI3.
 * 
 * Expects each CAS to contain at least one NgramAnnotation.
 * Processes each NgramAnnotation by adding a corresponding AnswerScoringAnnotation to the CAS.
 * 
 * This annotator has no parameters and requires no initialization method.
 */

public class ScoreAnnotator extends CasAnnotator_ImplBase {	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void process(CAS aCas) throws AnalysisEngineProcessException {
		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
		
		// Get the Ngram Annotations for each Test Element in the document
		FSIndex<NgramAnnotation> ngramIndex = (FSIndex) jcas.getAnnotationIndex(NgramAnnotation.type);

		// Iterate over them in sequence
		for(NgramAnnotation ngramAnnot : ngramIndex)
		{			
			//////////////////////
			// Handle the question
			// Get the ngrams for this Test Element's question
			NgramSet questionNgrams = ngramAnnot.getQuestionNgrams();

			//////////////////////
			// Handle the answers
			// Create a list to hold our scoring for each answer choice
			PassageScoring output = new PassageScoring(jcas);
			output.setComponentId(this.getClass().getName());
			output.setBegin(Math.min(questionNgrams.getBegin(), getMinBegin(ngramAnnot.getPassageNgrams())));
			output.setEnd(Math.max(questionNgrams.getEnd(), getMaxEnd(ngramAnnot.getPassageNgrams())));
			
			/////////////////////
			// Score each passage
			FSList passages = ngramAnnot.getPassageNgrams();
			FSList scores = new EmptyFSList(jcas);
			while(!(passages instanceof EmptyFSList))
			{
				NgramSet passageNgrams = (NgramSet) ((NonEmptyFSList) passages).getHead();
				NonEmptyFSList next = new NonEmptyFSList(jcas);
				ScoredSpan score = new ScoredSpan(jcas);
				score.setBegin(passageNgrams.getBegin());
				score.setEnd(passageNgrams.getEnd());
				score.setText(passageNgrams.getText());
				score.setComponentId(this.getClass().getName());
				score.setScore(this.score(questionNgrams,passageNgrams));
				score.addToIndexes();
				next.setHead(score);
				next.setTail(scores);
				scores = next;
				passages = ((NonEmptyFSList) passages).getTail();
			}
			output.setScores(scores);
			output.setBegin(ngramAnnot.getBegin());
			output.setEnd(ngramAnnot.getEnd());
			output.setComponentId(this.getClass().getName());	
			output.addToIndexes();
		}
	}

	/**
	 * Auxiliary method to compute the smallest begin index of the Annotations in arr 
	 * @param arr the array of Annotations to look at
	 * @return the minimum begin index
	 */
	private int getMinBegin(FSList arr) {
		int min =  Integer.MAX_VALUE;
		while(!(arr instanceof EmptyFSList))
		{
			int begin = ((Annotation) ((NonEmptyFSList) arr).getHead()).getBegin();
			if(begin < min)
				min = begin;
		}
		return min;
	}

	/**
	 * Auxiliary method to compute the largest end index of the Annotations in arr 
	 * @param arr the array of Annotations to look at
	 * @return the maximum end index
	 */
	private int getMaxEnd(FSList arr) {
		int max = Integer.MIN_VALUE;
		while(!(arr instanceof EmptyFSList))
		{
			int end = ((Annotation) ((NonEmptyFSList) arr).getHead()).getBegin();
			if(end > max)
				max = end;
		}
		return max;
	}
	
	/**
	 * Scores the agreement between the two NgramSet params based on ngram overlap 
	 * 
	 * @return
	 */
	private Double score(NgramSet tokens1, NgramSet tokens2)
	{	
		return (double) 0; //TODO: Do something actually interesting!
	}
}
