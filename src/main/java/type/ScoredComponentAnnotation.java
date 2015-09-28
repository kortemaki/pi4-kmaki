

/* First created by JCasGen Mon Sep 28 10:15:23 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** A component annotation with a score
 * Updated by JCasGen Mon Sep 28 13:54:26 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI4/pi4-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class ScoredComponentAnnotation extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ScoredComponentAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ScoredComponentAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ScoredComponentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ScoredComponentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ScoredComponentAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets The score for this annotation
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (ScoredComponentAnnotation_Type.featOkTst && ((ScoredComponentAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ScoredComponentAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ScoredComponentAnnotation_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets The score for this annotation 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (ScoredComponentAnnotation_Type.featOkTst && ((ScoredComponentAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ScoredComponentAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ScoredComponentAnnotation_Type)jcasType).casFeatCode_score, v);}    
  }

    