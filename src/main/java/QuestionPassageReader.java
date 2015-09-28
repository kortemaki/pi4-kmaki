import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;

/**
 * This Collection Reader serves as a reader to parse your input. This is just template code, so you
 * need to implement actual code.
 */
public class QuestionPassageReader extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a directory containing input
	 * files.
	 */
	public static final String PARAM_INPUTDIR = "InputDir";
	
	/**
	 * Name of configuration parameter that contains the character encoding used by the input files.
	 * If not specified, the default system encoding will be used.
	 */
	public static final String PARAM_ENCODING = "Encoding";

	/**
	 * Name of optional configuration parameter that contains the language of the documents in the
	 * input directory. If specified this information will be added to the CAS.
	 */
	public static final String PARAM_LANGUAGE = "Language";

	private static final String filename = "questions_and_passages.txt";

	private Map<Integer,String> questions;
	private Map<Integer,List<String>> passages;
	private Iterator<Integer> QuestionIterator;
	private String mEncoding;
	private String mLanguage;
	private String URI;
	
	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	@SuppressWarnings("deprecation")
	public void initialize() throws ResourceInitializationException {
		File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
		this.mEncoding  = (String) getConfigParameterValue(PARAM_ENCODING);
		this.mLanguage  = (String) getConfigParameterValue(PARAM_LANGUAGE);
		
		// if input directory does not exist or is not a directory, throw exception
		if (!directory.exists() || !directory.isDirectory()) {
			throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR, this.getMetaData().getName(), directory.getPath() });
		}

		// open input stream to file
		File file = new File(filename);
		String text = "";
		try {
			text = FileUtils.file2String(file, mEncoding);
		} catch (IOException e) {
			throw new ResourceInitializationException("Error while opening file " + filename, null);
		}

		//Save some metadata about the file
		try {
			this.URI = file.getAbsoluteFile().toURL().toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Get the questions and passages and index based on question number
		this.questions = new TreeMap<Integer,String>();
		this.passages = new TreeMap<Integer,List<String>>();

		for(String line : text.split("\n"))
		{
			int qnum = getQnum(line);
			//if question
			if(isQuestion(line))
			{
				questions.put(qnum, line);
			}
			else
			{
				if(!passages.containsKey(qnum))
					passages.put(qnum, new ArrayList<String>());
				passages.get(qnum).add(line);
			}
		}
	}	
	
	private boolean isQuestion(String line) {
		// TODO Auto-generated method stub
		return false;
	}

	private static Integer getQnum(String line) {
		Scanner scan = new Scanner(line);
		int num = scan.nextInt();
		scan.close();
		return num;
	}
	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		//Can we even get another question?
		if(!this.hasNext())
			throw new CollectionException(); //No further questions, Your Honor!

		//Get the next question
		Integer qnum = this.QuestionIterator.next();
		
		//Identify relevant texts
		String question = this.questions.get(qnum);
		List<String> passages = this.passages.get(qnum);

		//Populate the CAS
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}

		// put document in CAS
				String text = question;
		jcas.setDocumentText(text);
		
		// set language if it was explicitly specified as a configuration parameter
		if (this.mLanguage != null) {
			jcas.setDocumentLanguage(this.mLanguage);
		}
		
		// Also store location of source document in CAS. This information is critical
		// if CAS Consumers will need to know where the original document contents are located.
		// For example, the Semantic Search CAS Indexer writes this information into the
		// search index that it creates, which allows applications that use the search index to
		// locate the documents that satisfy their semantic queries.
		SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
		srcDocInfo.setUri(this.URI);
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize(text.length());
		srcDocInfo.setLastSegment(!this.hasNext());
		srcDocInfo.addToIndexes();

	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public Progress[] getProgress() {
		return null;
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return this.QuestionIterator.hasNext();
	}

}
