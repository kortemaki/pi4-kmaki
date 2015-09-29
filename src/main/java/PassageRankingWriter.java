
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


import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.resource.ResourceProcessException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasToInlineXml;

import type.OutputAnnotation;



/**
 * This CAS Consumer serves as a writer to generate the output. 
 * 
 * Just writes the output annotations for the CAS to the output file!
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {


	public static final String PARAM_OUTPUTDIR = "OutputDir";

	private File mOutputDir;

	private PrintWriter outfile;

	private static final String OUTFILE = "passageRanking.txt";

	public void initialize() throws ResourceInitializationException {
		mOutputDir = new File(((String) getConfigParameterValue(PARAM_OUTPUTDIR)).trim());
		if (!mOutputDir.exists()) {
			mOutputDir.mkdirs();
		}

		try {
			this.outfile = new PrintWriter(
					new BufferedWriter(
							new FileWriter(
									new File(mOutputDir, OUTFILE), 
									false)));
		} catch (IOException e) {
			Object[] args = new Object[1];
			args[0] = e;
			throw new ResourceInitializationException("Cannot open output file!",args);
		}
	}


	@Override
	public void processCas(CAS arg0) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = arg0.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		FSIndex<OutputAnnotation> it = (FSIndex) jcas.getAnnotationIndex(OutputAnnotation.type);

		for(OutputAnnotation out : it)
		{
			outfile.println(out.getOutput());
		}
	}
	
	@Override
	public void destroy()
	{
		outfile.close();
	}
}
