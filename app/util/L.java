package util;

import java.util.ArrayList;

public class L {

	ArrayList list;
	
	public L(Object element) {
		list = new ArrayList();
		list.add(element);
	}
	
	public L add(Object element) {
		list.add(element);
		return this;
	}
	
	public ArrayList list() {
		return list;
	}
	
	public static L make(Object element) {
		return new L(element);
	}
}
