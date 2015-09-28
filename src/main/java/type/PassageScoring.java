

/* First created by JCasGen Mon Sep 28 12:13:36 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;


/** Annotation holding the ranking scores for each of the passages associated with this Test Element.
 * Updated by JCasGen Mon Sep 28 12:13:47 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI4/pi4-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class PassageScoring extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PassageScoring.class);
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
  protected PassageScoring() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PassageScoring(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PassageScoring(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PassageScoring(JCas jcas, int begin, int end) {
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
  //* Feature: scores

  /** getter for scores - gets The list of scores for each of the respective passages associated with this Test Element.
   * @generated
   * @return value of the feature 
   */
  public FSList getScores() {
    if (PassageScoring_Type.featOkTst && ((PassageScoring_Type)jcasType).casFeat_scores == null)
      jcasType.jcas.throwFeatMissing("scores", "type.PassageScoring");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PassageScoring_Type)jcasType).casFeatCode_scores)));}
    
  /** setter for scores - sets The list of scores for each of the respective passages associated with this Test Element. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScores(FSList v) {
    if (PassageScoring_Type.featOkTst && ((PassageScoring_Type)jcasType).casFeat_scores == null)
      jcasType.jcas.throwFeatMissing("scores", "type.PassageScoring");
    jcasType.ll_cas.ll_setRefValue(addr, ((PassageScoring_Type)jcasType).casFeatCode_scores, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    