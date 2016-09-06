//Class Character.Demostrate several is... methods
class IsDemo{
	public static void main(String[] args){
		char[] a={'a','b','5','?','A',' ','\t','\n'};
		for(int i=0;i<a.length;i++){
			if(Character.isDigit(a[i]))
				System.out.println(a[i]+" is a digit.");
			if(Character.isLetter(a[i]))
				System.out.println(a[i]+" is a letter.");
			if(Character.isLetterOrDigit(a[i]))
				System.out.println(a[i]+" is a letter or digit.");
			if(Character.isWhitespace(a[i])){
				String chStr="";
				if(a[i]==' ')
					chStr="<space>";
				else if(a[i]=='\t')
					chStr="<tab>";
				else if(a[i]=='\n')
					chStr="<newline>";
				System.out.println(chStr+" is whitespace.");
			}
			if(Character.isSpaceChar(a[i])){
				String chStr="";
				if(a[i]==' ')
					chStr="<space>";
				else if(a[i]=='\t')
					chStr="<tab>";
				else if(a[i]=='\n')
					chStr="<newline>";
				System.out.println(chStr+" is space.");
				
			}
			if(Character.isUpperCase(a[i]))
				System.out.println(a[i]+" is uppercase.");
			if(Character.isLowerCase(a[i]))
				System.out.println(a[i]+" is lowercase.");
			System.out.println();
		}
	}
}
