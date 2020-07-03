package com.ljnt.tree;

import java.io.*;
import java.util.*;

/**
 * @ Program       :  com.ljnt.tree.HuffmanCode
 * @ Description   :  赫夫曼编码
 * @ Author        :  lj
 * @ CreateDate    :  2020-5-31 18:53
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String m = "i like like like java do you like a java";
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree(m);
        Map<Byte, String> huffmanCodemap = huffmanCodeTree.getCode();
        System.out.println(huffmanCodemap);
        byte[] codeBytearr = huffmanCodeTree.getZip(huffmanCodemap);
        System.out.println(Arrays.toString(codeBytearr));
        String otext = new String(huffmanCodeTree.deCode(huffmanCodemap, codeBytearr));
        System.out.println(otext);
//        HuffmanCodeTree huffmanCodeTree=new HuffmanCodeTree();
//        huffmanCodeTree.zipFile("E://p.html","E://p.code");
//        huffmanCodeTree.unZipFile("E://p.code","E://pdecode.html");
    }
}

class HuffmanCodeTree {
    CodeTreeNode root;
    String oText;//原文
    char[] oTextChararr;//原文char数组
    byte[] oBytearr;//原文byte数组
    byte[] codeBytearr;//编码压缩后byte数组
    Map<Byte, String> huffmanCodemap = new HashMap<>();//编码后的map<原文字符byte,编码值>
    Map<Byte, Integer> oTextmap = new HashMap<>();//原文字符后的map<原文字符byte,权值（次数）>

    public HuffmanCodeTree() {
    }

    public HuffmanCodeTree(String oText) {
        this.oText = oText;
        stringcreateHuffmanCodeTree(oText);
    }

    /**
     * @param : [oText原文]
     * @return : void
     * @throws :
     * @Description ：字符串创建赫夫曼树
     * @author : lj
     * @date : 2020-6-2 21:26
     */
    public void stringcreateHuffmanCodeTree(String oText) {
        List<CodeTreeNode> nodelist = new ArrayList<>();
        oTextChararr = oText.toCharArray();
        oBytearr = new byte[oTextChararr.length];
        for (int i = 0; i < oTextChararr.length; i++) {
            oBytearr[i] = (byte) oTextChararr[i];
            if (oTextmap.get(oBytearr[i]) == null) {
                oTextmap.put(oBytearr[i], 1);
            } else {
                oTextmap.replace(oBytearr[i], oTextmap.get(oBytearr[i]) + 1);
            }
        }
        for (Map.Entry<Byte, Integer> en : oTextmap.entrySet()) {
            nodelist.add(new CodeTreeNode(en.getValue(), en.getKey()));
        }
        root = createHuffmanCodeTree(nodelist);
    }

    /**
     * @param : [barr待编码原文byte数组]
     * @return : void
     * @throws :
     * @Description ：byte数组创建赫夫曼树
     * @author : lj
     * @date : 2020-6-2 21:27
     */
    public void byteArrcreateHuffmanCodeTree(byte[] barr) {
        oBytearr = barr;
        List<CodeTreeNode> nodelist = new ArrayList<>();
        for (int i = 0; i < barr.length; i++) {
            if (oTextmap.get(barr[i]) == null) {
                oTextmap.put(barr[i], 1);
            } else {
                oTextmap.replace(barr[i], oTextmap.get(barr[i]) + 1);
            }
        }
        for (Map.Entry<Byte, Integer> en : oTextmap.entrySet()) {
            nodelist.add(new CodeTreeNode(en.getValue(), en.getKey()));
        }
        root = createHuffmanCodeTree(nodelist);
    }

    /**
     * @param : [nodelist节点列表]
     * @return : com.ljnt.tree.CodeTreeNode
     * @throws :
     * @Description ：创建赫夫曼树
     * @author : lj
     * @date : 2020-6-2 21:29
     */
    public CodeTreeNode createHuffmanCodeTree(List<CodeTreeNode> nodelist) {

        Collections.sort(nodelist);
        while (nodelist.size() > 1) {
            CodeTreeNode node1 = nodelist.get(0);
            CodeTreeNode node2 = nodelist.get(1);
            CodeTreeNode newnode = new CodeTreeNode(node1.weight + node2.weight, null);
            newnode.setLeft(node1);
            newnode.setRight(node2);
            nodelist.remove(node1);
            nodelist.remove(node2);
            nodelist.add(newnode);
            Collections.sort(nodelist);
        }
        return nodelist.get(0);
    }


    public void preOrder() {
        preOrder(root);
    }

    /**
     * @param : [root]
     * @return : void
     * @throws :
     * @Description ：前序遍历
     * @author : lj
     * @date : 2020-6-2 21:30
     */
    public void preOrder(CodeTreeNode root) {
        if (root != null) {
            System.out.println(root);
            if (root.left != null) {
                preOrder(root.left);
            }
            if (root.right != null) {
                preOrder(root.right);
            }
        }
    }

    public Map getCode() {
        if (root == null) {
            return null;
        } else {
            if (root.left != null) {
                //获取左子树编码值
                getCode(root.left, "0", new StringBuffer());
            }
            if (root.right != null) {
                //获取右子树编码值
                getCode(root.right, "1", new StringBuffer());
            }
        }
        return huffmanCodemap;

    }

    /**
     * @param : [node, code, stringBuffer]
     * @return : void
     * @throws :
     * @Description ：获取赫夫曼编码值
     * @author : lj
     * @date : 2020-6-2 21:31
     */
    public void getCode(CodeTreeNode node, String code, StringBuffer stringBuffer) {
        //注意：每一次递归都要创建一个新的StringBuffer，否则会一直引用之前的StringBuffer，编码值一直拼接下去
        StringBuffer stringBuffer1 = new StringBuffer(stringBuffer);
        stringBuffer1.append(code);
        if (node.content == null) {
            getCode(node.left, "0", stringBuffer1);
            getCode(node.right, "1", stringBuffer1);
        } else {
            huffmanCodemap.put(node.getContent(), stringBuffer1.toString());
            return;
        }
    }

    /**
     * @param : [huffmanCodemap]
     * @return : byte[]
     * @throws :
     * @Description ：压缩编码，即将编码值（二进制）转成byte数组
     * @author : lj
     * @date : 2020-6-2 21:34
     */
    public byte[] getZip(Map<Byte, String> huffmanCodemap) {
        StringBuffer codeedText = new StringBuffer();
        for (int i = 0; i < oBytearr.length; i++) {
            codeedText.append(huffmanCodemap.get(oBytearr[i]));
        }
        //8位二进制为1byte,所以编码压缩成byte数组需要分成codeedText.length() / 8 + 1段，
        // 也就是byte数组的长度为这么长，而最后一个位置用来保存最后一段的编码的长度，
        // 因为如果最后一段转byte为正数就会出现如 011-转byte->(byte)3-转二进制->11 的情况,
        // 所以需要保存它的长度来保证解码的时候在最后一段前补多少0
        codeBytearr = new byte[codeedText.length() / 8 + 2];
        for (int i = 0, j = 0; i < codeedText.length(); i += 8, j++) {
            if (i + 8 > codeedText.length()) {
                String s = codeedText.substring(i);
                byte b = (byte) Integer.parseInt(s, 2);
                codeBytearr[j] = b;
                codeBytearr[j + 1] = (byte) s.length();
            } else {
                String s = codeedText.substring(i, i + 8);
                byte b = (byte) Integer.parseInt(s, 2);
                codeBytearr[j] = b;
            }
        }
        return codeBytearr;
    }

    /**
     * @param : [huffmanCodemap, codeBytearr]
     * @return : byte[]
     * @throws :
     * @Description ：解码
     * @author : lj
     * @date : 2020-6-2 21:45
     */
    public byte[] deCode(Map<Byte, String> huffmanCodemap, byte[] codeBytearr) {
        StringBuffer deCode2text = new StringBuffer();
        for (int i = 0; i < codeBytearr.length - 1; i++) {
            if (codeBytearr[i] >= 0) {//如果是正数需要在前面补0，补够8位
                int temp = codeBytearr[i];
                temp |= 256;//如：temp=1, 1|256=>000000001|100000000=100000001，我们截取后面8位就ok
                String s = Integer.toBinaryString(temp);
                if (i == codeBytearr.length - 2) {//最后一段需要截取的长度为codeBytearr[i + 1]
                    deCode2text.append(s.substring(s.length() - codeBytearr[i + 1]));
                } else {//其他的就截取8位
                    deCode2text.append(s.substring(s.length() - 8));
                }

            } else {
                String s = Integer.toBinaryString(codeBytearr[i]);
                //负数也要截取8位,因为Integer为16位
                deCode2text.append(s.substring(s.length() - 8));
            }
        }
        Map<String, Byte> huffmanCodemaprev = new HashMap<>();
        for (Map.Entry<Byte, String> m : huffmanCodemap.entrySet()) {
            huffmanCodemaprev.put(m.getValue(), m.getKey());
        }
        StringBuffer stemp = new StringBuffer();
        List<Byte> bl = new ArrayList<>();
        for (int i = 0; i < deCode2text.length(); i++) {
            stemp.append(deCode2text.charAt(i));
            Byte c = huffmanCodemaprev.get(stemp.toString());
            if (c != null) {
                bl.add(c);
                stemp = new StringBuffer();
            }
        }
        byte[] bb = new byte[bl.size()];
        for (int i = 0; i < bl.size(); i++) {
            bb[i] = bl.get(i);
        }
        return bb;
    }

    public void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byteArrcreateHuffmanCodeTree(b);
            Map<Byte, String> huffmanCodemap = getCode();
            byte[] huffmanBytes = getZip(huffmanCodemap);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodemap);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取 byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = deCode(huffmanCodes, huffmanBytes);
            //将 bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

}


class CodeTreeNode implements Comparable<CodeTreeNode> {
    int weight;
    Byte content;
    CodeTreeNode left;
    CodeTreeNode right;

    public CodeTreeNode(int weight, Byte content) {
        this.weight = weight;
        this.content = content;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Byte getContent() {
        return content;
    }

    public void setContent(Byte content) {
        this.content = content;
    }

    public CodeTreeNode getLeft() {
        return left;
    }

    public void setLeft(CodeTreeNode left) {
        this.left = left;
    }

    public CodeTreeNode getRight() {
        return right;
    }

    public void setRight(CodeTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "CodeTreeNode{" +
                "weight=" + weight +
                ", content=" + content +
                '}';
    }

    @Override
    public int compareTo(CodeTreeNode o) {
        return this.weight - o.weight;
    }
}

