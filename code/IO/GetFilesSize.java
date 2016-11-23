package IO;
import static org.junit.Assert.*;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
//import com.sun.management.OperatingSystemMXBean;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

/**
 * 测试获取系统硬盘使用的例子
 * 2016年11月23日 上午10:08:12
 */
public class GetFilesSize {
	@Test
	public void diskUseTest() {
		File[] roots = File.listRoots();//获取磁盘分区列表     
		for (File file : roots) {     
			System.out.println(file.getPath()+"信息如下:");     
		   System.out.println("空闲未使用 = " + file.getFreeSpace()/1024/1024/1024+"G");//空闲空间     
		   System.out.println("已经使用 = " + file.getUsableSpace()/1024/1024/1024+"G");//可用空间     
		   System.out.println("总容量 = " + file.getTotalSpace()/1024/1024/1024+"G");//总空间     
		   System.out.println("空闲未使用 = " + file.getFreeSpace()/1024/1024+"M");//空闲空间     
		   System.out.println("已经使用 = " + file.getUsableSpace()/1024/1024+"M");//可用空间     
		   System.out.println("总容量 = " + file.getTotalSpace()/1024/1024+"M");//总空间     
		   System.out.println("空闲未使用 = " + file.getFreeSpace());//空闲空间     
		   System.out.println("已经使用 = " + file.getUsableSpace());//可用空间     
		   System.out.println("总容量 = " + file.getTotalSpace());//总空间     
		   System.out.println();
		}     
	}
	@Test
	public void memoryUseTest() {
//		 OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();     
//	     System.out.println("系统物理内存总计：" + osmb.getTotalPhysicalMemorySize() / 1024/1024 + "MB");     
//	     System.out.println("系统物理可用内存总计：" + osmb.getFreePhysicalMemorySize() / 1024/1024 + "MB");
	}
	/**
	 * 参考：http://www.jb51.net/article/42298.htm 
	 * 单线程递归方式获取全部文件大小
	 */
	@Test
	public void getTotalSizeOfFilesInDir(){
		String fileName = "C:\\Users\\Administrator\\Pictures";
		final long start = System.nanoTime();
	    final long total = getTotalSizeOfFilesInDir(new File(fileName));
	    final long end = System.nanoTime();
        System.out.println("Total Size: " + total/1024 +"KB");
        System.out.println("Time taken: " + (end - start) / 1.0e9);
//        Total Size: 339305KB
//        Time taken: 0.001767345
	}
	 /**
	  * 递归方式 计算文件的大小
	  * @param file
	  * @return
	  */
    private long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }
    /**
     * 使用Executors.newFixedThreadPool和callable 多线程实现
     * @throws TimeoutException 
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    @Test
	public void getTotalSizeOfFilesInDir2() throws InterruptedException, ExecutionException, TimeoutException{
		String fileName = "C:\\Users\\Administrator\\Pictures";
		final long start = System.nanoTime();
	    final long total = getTotalSizeOfFilesInDir2(new File(fileName));
	    final long end = System.nanoTime();
        System.out.println("Total Size: " + total/1024 +"KB");
        System.out.println("Time taken: " + (end - start) / 1.0e9);
//        Total Size: 339305KB
//        Time taken: 0.006615501
	}
    class SubDirectoriesAndSize {
        final public long size;
        final public List<File> subDirectories;
        public SubDirectoriesAndSize(final long totalSize,
                final List<File> theSubDirs) {
            size = totalSize;
            subDirectories = Collections.unmodifiableList(theSubDirs);
        }
    }
	private SubDirectoriesAndSize getTotalAndSubDirs(File file){
	   long total = 0;
	    final List<File> subDirectories = new ArrayList<File>();
	    if (file.isDirectory()) {
	        final File[] children = file.listFiles();
	        if (children != null)
	            for (final File child : children) {
	                if (child.isFile())
	                    total += child.length();
	                else
	                    subDirectories.add(child);
	            }
	    }
	    return new SubDirectoriesAndSize(total, subDirectories);
	}
	 private long getTotalSizeOfFilesInDir2(final File file)throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
                for (final File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirectoriesAndSize>() {
                                public SubDirectoriesAndSize call() {
                                    return getTotalAndSubDirs(directory);
                                }
                            }));
                }
                directories.clear();
                for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture
                            .get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        } finally {
            service.shutdown();
        }
    }
	 /**
	  * 使用Executors.newFixedThreadPool和callable 多线程的另外一种实现
	 * @throws TimeoutException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	  */
	@Test
	public void getTotalSizeOfFilesInDir3() throws InterruptedException, ExecutionException, TimeoutException{
		String fileName = "C:\\Users\\Administrator\\Pictures";
		final long start = System.nanoTime();
	    final long total = getTotalSizeOfFile3(fileName);
	    final long end = System.nanoTime();
        System.out.println("Total Size: " + total/1024 +"KB");
        System.out.println("Time taken: " + (end - start) / 1.0e9);
//        Total Size: 339305KB
//        Time taken: 0.009987683

	}
	 private long getTotalSizeOfFilesInDir3(final ExecutorService service, final File file) 
	            		throws InterruptedException, ExecutionException,TimeoutException {
	        if (file.isFile())
	            return file.length();
	        long total = 0;
	        final File[] children = file.listFiles();

	        if (children != null) {
	            final List<Future<Long>> partialTotalFutures = new ArrayList<Future<Long>>();
	            for (final File child : children) {
	                partialTotalFutures.add(service.submit(new Callable<Long>() {
	                    public Long call() throws InterruptedException,
	                            ExecutionException, TimeoutException {
	                        return getTotalSizeOfFilesInDir3(service, child);
	                    }
	                }));
	            }

	            for (final Future<Long> partialTotalFuture : partialTotalFutures)
	                total += partialTotalFuture.get(100, TimeUnit.SECONDS);
	        }
	        return total;
	    }
	 private long getTotalSizeOfFile3(final String fileName)
	            throws InterruptedException, ExecutionException, TimeoutException {
	        final ExecutorService service = Executors.newFixedThreadPool(100);
	        try {
	            return getTotalSizeOfFilesInDir3(service, new File(fileName));
	        } finally {
	            service.shutdown();
	        }
	    }
	 
	@Test
	public void getTotalSizeOfFilesInDir4() throws InterruptedException, ExecutionException, TimeoutException{
		String fileName = "C:\\Users\\Administrator\\Pictures";
		final long start = System.nanoTime();
	    final long total = getTotalSizeOfFile3(fileName);
	    final long end = System.nanoTime();
        System.out.println("Total Size: " + total/1024 +"KB");
        System.out.println("Time taken: " + (end - start) / 1.0e9);
//	        Total Size: 339305KB
//	        Time taken: 0.009987683

	}
}