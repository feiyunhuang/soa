package dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Random;

public class dom {

    public void createXMLByDOM(File dest) {

        String info[][]= {
                {"蔡赵辰","151250004","男","21","151250004@smail.nju.edu.cn"},
                {"李珍鸿","151250089","男","21","151250089@smail.nju.edu.cn"},
                {"万年杰","151250135","男","21","151250135@smail.nju.edu.cn"},
                {"刘一澎","151250099","男","21","151250099@smail.nju.edu.cn"},
                {"唐骞","151250131","男","21","151250131@smail.nju.edu.cn"},
                {"常德隆","151250011","男","21","151250011@smail.nju.edu.cn"},
                {"曹鸿荣","151250006","男","21","151250006@smail.nju.edu.cn"},
                {"黄韵斐","151250065","男","21","151250065@smail.nju.edu.cn"},
                {"曹畅","151250005","女","21","151250005@smail.nju.edu.cn"},
                {"朱润之","151250213","男","21","151250213@smail.nju.edu.cn"},
                {"江回晖","151250069","男","21","151250069@smail.nju.edu.cn"},
                {"贾莛","151250068","男","21","151250068@smail.nju.edu.cn"}
        };

        String cl[]={"编译原理","离散数学","数据结构","操作系统","计算机网络"};

        String scoretype[]={"平时成绩","期末成绩","总评成绩"};

        // 创建DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            // 创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 创建Document
            Document document = builder.newDocument();

            // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
            // document.setXmlStandalone(true);

            // 创建根节点
            Element studentlist = document.createElement("学生列表");
            studentlist.setAttribute("xmlns","http://jw.nju.edu.cn/schema");

            // 创建子节点，并设置属性


            for(int i=0;i<12;i++) {
                Element student = document.createElement("学生");
                student.setAttribute("学号", info[i][1]);

                // 为book添加子节点
                Element academy = document.createElement("软件学院");
                academy.setTextContent("软件学院");
                student.appendChild(academy);

                Element personinfo = document.createElement("个人信息");
                personinfo.setAttribute("学号", info[i][1]);
                student.appendChild(personinfo);


                Element name = document.createElement("姓名");
                name.setTextContent(info[i][0]);
                personinfo.appendChild(name);
                Element sex = document.createElement("性别");
                sex.setTextContent(info[i][2]);
                personinfo.appendChild(sex);
                Element age = document.createElement("年龄");
                age.setTextContent(info[i][3]);
                personinfo.appendChild(age);
                Element phone = document.createElement("联系方式");
                phone.setTextContent(info[i][4]);
                personinfo.appendChild(phone);


                Element scorelist = document.createElement("成绩列表");
                student.appendChild(scorelist);

                for(int j=0;j<5;j++) {
                    int dailyscore=random(40,100);
                    int examscore=random(40,100);
                    int totalscore=(int)(0.4*dailyscore+0.6*examscore);

                    for(int k=0;k<3;k++) {
                        Element coursescore = document.createElement("课程成绩");
                        coursescore.setAttribute("课程编号", cl[j]);
                        coursescore.setAttribute("成绩性质", scoretype[k]);
                        scorelist.appendChild(coursescore);

                        Element number = document.createElement("学号");
                        number.setTextContent(info[i][1]);
                        coursescore.appendChild(number);

                        int s;

                        if(k==0){
                            s=dailyscore;
                        }
                        else if(k==1){
                            s=examscore;
                        }
                        else{
                            s=totalscore;
                        }
                        Element score = document.createElement("成绩");
                        score.setTextContent(String.valueOf(s));
                        coursescore.appendChild(score);

                    }
                }

                // 为根节点添加子节点
                studentlist.appendChild(student);
            }


            // 将根节点添加到Document下
            document.appendChild(studentlist);

            /*
             * 下面开始实现： 生成XML文件
             */

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();

            // 创建Transformer对象
            Transformer tf = tff.newTransformer();

            // 设置输出数据时换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            // 使用Transformer的transform()方法将DOM树转换成XML
            tf.transform(new DOMSource(document), new StreamResult(dest));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



        public static int random (int min,int max) {

            Random random = new Random();

            int s = random.nextInt(max)%(max-min+1) + min;
            return s;
        }

}