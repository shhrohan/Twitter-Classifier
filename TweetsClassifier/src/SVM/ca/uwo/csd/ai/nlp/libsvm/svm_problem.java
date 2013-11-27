package SVM.ca.uwo.csd.ai.nlp.libsvm;

public class svm_problem implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	public int l;
    public double[] y;
    public svm_node[] x;

    public svm_problem(int l, double[] y, svm_node[] x) 
    {
        this.l = l;
        this.y = y;
        this.x = x;
    }

    public svm_problem() {
    }
    
}
