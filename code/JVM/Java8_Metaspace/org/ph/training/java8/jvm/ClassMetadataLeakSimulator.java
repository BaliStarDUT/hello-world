package org.ph.training.java8.jvm;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * PermGenRemovalValidator
 *
 * @author Pierre-Hugues Charbonneau
 *  java -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m  -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError org/ph/training/java8/jvm/ClassMetadataLeakSimulator
 */
public class ClassMetadataLeakSimulator {

	private static Map<String, ClassA> classLeakingMap = new HashMap<String, ClassA>();
	private final static int NB_ITERATIONS_DEFAULT = 50000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Class metadata leak simulator");
		System.out.println("Author: Pierre-Hugues Charbonneau");
		System.out.println("http://javaeesupportpatterns.blogspot.com");

		int nbIterations = (args != null && args.length == 1) ? Integer.parseInt(args[0]) : NB_ITERATIONS_DEFAULT;

		try {

			for (int i = 0; i < nbIterations; i++) {

				String fictiousClassloaderJAR = "file:" + i + ".jar";

				URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

				// Create a new classloader instance
				URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);

				// Create a new Proxy instance
				ClassA t = (ClassA) Proxy.newProxyInstance(newClassLoader,
						new Class<?>[] { ClassA.class },
						new ClassAInvocationHandler(new ClassAImpl()));

				// Add the new Proxy instance to the leaking HashMap
				classLeakingMap.put(fictiousClassloaderJAR, t);
			}
		}
		catch (Throwable any) {
			System.out.println("ERROR: " + any);
		}

		System.out.println("Done!");
	}
}
