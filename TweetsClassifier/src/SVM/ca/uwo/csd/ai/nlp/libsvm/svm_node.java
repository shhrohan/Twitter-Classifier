package SVM.ca.uwo.csd.ai.nlp.libsvm;

public class svm_node implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	public Object data;

    public svm_node() {
    }

    public svm_node(Object data) {
        this.data = data;
    }
}
