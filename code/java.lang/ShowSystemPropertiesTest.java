//show system property
class ShowSystemPropertiesTest{
	public static void main(String[] args){
		//系统属性类Properties类
		java.util.Properties properties = System.getProperties();
		properties.list(System.out);

		System.out.println("user.dir: "+System.getProperty("user.dir"));
		System.out.println("java.version: "+System.getProperty("java.version"));
		System.out.println("os.name: "+System.getProperty("os.name"));
		System.out.println("os.arch"+System.getProperty("os.arch"));
		System.out.println("os.version"+System.getProperty("os.version"));
		System.out.println("file.separator: "+System.getProperty("file.separator"));

		System.out.println("java.vm.version: "+System.getProperty("java.vm.version"));
		System.out.println("java.class.path"+System.getProperty("java.class.path"));
		System.out.println("java.class.version"+System.getProperty("java.class.version"));
		System.out.println("java.compiler"+System.getProperty("java.compiler"));
		System.out.println("java.ext.dirs"+System.getProperty("java.home"));
		System.out.println("java.io.tmpdir"+System.getProperty("java.io.tmpdir"));
		System.out.println("java.library.path"+System.getProperty("java.library.path"));
		System.out.println("java.specification.name"+System.getProperty("java.specification.name"));
		System.out.println("java.specification.vendor"+System.getProperty("java.specification.vendor"));
		System.out.println("java.specification.version: "+System.getProperty("java.specification.version"));
	}
}
