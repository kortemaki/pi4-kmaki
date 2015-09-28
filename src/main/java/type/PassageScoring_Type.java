
/* First created by JCasGen Mon Sep 28 12:13:36 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Annotation holding the ranking scores for each of the passages associated with this Test Element.
 * Updated by JCasGen Mon Sep 28 18:37:17 EDT 2015
 * @generated */
public class PassageScoring_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PassageScoring_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PassageScoring_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PassageScoring(addr, PassageScoring_Type.this);
  			   PassageScoring_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PassageScoring(addr, PassageScoring_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = PassageScoring.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.PassageScoring");
 
  /** @generated */
  final Feature casFeat_scores;
  /** @generated */
  final int     casFeatCode_scores;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getScores(int addr) {
        if (featOkTst && casFeat_scores == null)
      jcas.throwFeatMissing("scores", "type.PassageScoring");
    return ll_cas.ll_getRefValue(addr, casFeatCode_scores);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setScores(int addr, int v) {
        if (featOkTst && casFeat_scores == null)
      jcas.throwFeatMissing("scores", "type.PassageScoring");
    ll_cas.ll_setRefValue(addr, casFeatCode_scores, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public PassageScoring_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_scores = jcas.getRequiredFeatureDE(casType, "scores", "uima.cas.FSList", featOkTst);
    casFeatCode_scores  = (null == casFeat_scores) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_scores).getCode();

  }
}



    