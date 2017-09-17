package cn.sycamore.util;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ClassUtilTest {

	   @Test
	    public void testField() throws  InvocationTargetException, IllegalAccessException {
	        System.out.println("=============�����ֶ�===============");
	        String[] fields = ClassUtil.getField("model.BusinessLog",true);
	        for (String f : fields) {
	            System.out.println(f);
	        }
	    }

	    @Test
	    public void testPublicField()  {
	        System.out.println("=============��ȡ���е�ȫ��public���԰����̳е�==========");
	        String[] fields = ClassUtil.getPublicField("model.BusinessLog", true);
	        for(String f:fields){
	            System.out.println(f);
	        }
	        System.out.println("=============��ȡ���е��Զ���public���԰����̳е�==========");
	        String[] fieldz = ClassUtil.getPublicField("model.BusinessLog", false);
	        for(String f:fieldz){
	            System.out.println(f);
	        }
	    }

	    @Test
	    public void testProtectedField()  {
	        System.out.println("=============��ȡ�����Զ����protected���͵�����==============");
	        String[] field = ClassUtil.getProtectedField("model.BusinessLog");
	        for(String f:field){
	            System.out.println(f);
	        }
	    }

	    @Test
	    public void testPrivateField()  {
	        System.out.println("=============��ȡ�����Զ����private���͵�����==============");
	        String[] field = ClassUtil.getPrivateField("model.BusinessLog");
	        for(String f:field){
	            System.out.println(f);
	        }
	    }
	    @Test
	    public void testPrivateMetod()  {
	        System.out.println("=============��ȡ�Զ����private���͵ķ���==========");
	        String[] methods = ClassUtil.getPrivateMethod("com.opslab.util.bean.BeanUtil");
	        for (String m : methods) {
	            System.out.println(m);
	        }

	    }
	    @Test
	    public void testProtectedMetod()  {
	        System.out.println("=============��ȡ�Զ����Protected���͵ķ���==========");
	        String[] methods = ClassUtil.getProtectedMethod("com.opslab.util.bean.BeanUtil", false);
	        for (String m : methods) {
	            System.out.println(m);
	        }
	        System.out.println("=============��ȡ�Զ���ĺͼ̳е�Protected���͵ķ���==========");
	        methods = ClassUtil.getProtectedMethod("com.opslab.util.bean.BeanUtil", true);
	        for (String m : methods) {
	            System.out.println(m);
	        }
	    }
	    @Test
	    public void testPublicMetod()  {
	        System.out.println("=============��ȡ�Զ����public���͵ķ���==========");
	        String[] methods = ClassUtil.getPublicMethod("com.opslab.util.bean.BeanUtil", false);
	        for (String m : methods) {
	            System.out.println(m);
	        }
	        System.out.println("=============��ȡ�Զ���ĺͼ̳е�public���͵ķ���==========");
	        methods = ClassUtil.getPublicMethod("com.opslab.util.bean.BeanUtil", true);
	        for (String m : methods) {
	            System.out.println(m);
	        }
	    }

	    @Test
	    public void testMetod() throws  InvocationTargetException, IllegalAccessException {
	        System.out.println("============���з���================");
	        String[] methods = ClassUtil.getMethod("com.opslab.util.StringUtil", false);
	        for (String m : methods) {
	            System.out.println(m);
	        }
	        System.out.println("=========�Զ���ķ����ͼ̳����ķ���===========");
	        methods = ClassUtil.getMethod("com.opslab.util.StringUtil", true);
	        for (String m : methods) {
	            System.out.println(m);
	        }

	    }


	    @Test
	    public void testgetClassNameByFile(){
	        String file = System.getProperty("user.dir")+"/target/classes";
	        List<String> classNames = ClassUtil.getClassNameByFile(file, true);
	        System.out.println(classNames);
	    }
	    @Test
	    public void testGetClass() {
	        System.out.println("============������================");
	        List<String> classNames = ClassUtil.getClassName("com.opslab", true);
	        for (String str : classNames) {
	            System.out.println(str);
	        }


	    }



	    @Test
	    public void testGetterAndSetter() throws InvocationTargetException, IllegalAccessException {

	        System.out.println("============Setter��Getter================");
	        BusinessLog log = new BusinessLog();
	        ClassUtil.setter(log, "operationName", "setter-method-test", String.class);
	        System.out.println(log);

	    }

	    /**
	     * ���Դ�jar���л�ȡ��������
	     */
	    @Test
	    public void testGetClassByJar(){
	        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
	        List<String> classNameList = ClassUtil.getClassNameByJar(jarPath);
	        System.out.println(classNameList);
	    }

	    /**
	     * ��ȡjar���е����е���Դ�ļ�
	     */
	    @Test
	    public void testGetResourceByJar(){
	        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
	        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath);
	        System.out.println(resourceNames);
	    }



	    /**
	     * ��ȡjar���е��Զ����͵���Դ
	     */
	    @Test
	    public void testGetResourceByJar1(){
	        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
	        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath,".xml");
	        System.out.println(resourceNames);
	    }

	    @Test
	    public void testGetSuperClass()  {
	        System.out.println(ClassUtil.getSuperClass("model.BusinessLog"));
	        System.out.println(ClassUtil.getSuperClass("model.Log"));
	    }

	    @Test
	    public void testGetSuperClassChian()  {
	        String[] superClassChian = ClassUtil.getSuperClassChian("model.BusinessLog");
	        System.out.println(Arrays.toString(superClassChian));
	        System.out.println(Arrays.toString(ClassUtil.getSuperClassChian("model.Log")));
	    }

	    @Test
	    public void testGetInterfaces()  {
	        String[] interfaces = ClassUtil.getInterfaces("java.util.List",false);
	        System.out.println(Arrays.toString(interfaces));
	        interfaces = ClassUtil.getInterfaces("model.BusinessLog",false);
	        System.out.println(Arrays.toString(interfaces));

	        interfaces = ClassUtil.getInterfaces("model.BusinessLog",true);
	        System.out.println(Arrays.toString(interfaces));
	    }

}
