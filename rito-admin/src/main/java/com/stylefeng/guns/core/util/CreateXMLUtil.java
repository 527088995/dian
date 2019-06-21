package com.stylefeng.guns.core.util;

import com.stylefeng.guns.modular.antiMoneyLaundering.model.AntiMoneyLaundering;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;


public class CreateXMLUtil {
    public static boolean start(AntiMoneyLaundering antiMoneyLaundering) {
        Long start = System.currentTimeMillis();
        boolean flag = createXml(antiMoneyLaundering);
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
        return flag;
    }


    /**
     * 生成xml方法
     */
    public static boolean createXml(AntiMoneyLaundering antiMoneyLaundering) {
        try {
            // 1、创建document对象
            Document document = (Document) DocumentHelper.createDocument();
            // 2、创建根节点rss
            Element rss = document.addElement("CSTR");
            // 4、生成子节点及子节点内容

            Element RBIF = rss.addElement("RBIF");
            Element RICD = RBIF.addElement("RICD");
            if (antiMoneyLaundering.getRicd() != null &&
                    !antiMoneyLaundering.getRicd().equals("")) {
                RICD.setText(antiMoneyLaundering.getRicd());
            }
            Element RPNC = RBIF.addElement("RPNC");
            if (antiMoneyLaundering.getRpnc() != null &&
                    !antiMoneyLaundering.getRpnc().equals("")) {
                RPNC.setText(antiMoneyLaundering.getRpnc());
            }
            Element RITP = RBIF.addElement("RITP");
            if (antiMoneyLaundering.getRitp() != null &&
                    !antiMoneyLaundering.getRitp().equals("")) {
                RITP.setText(antiMoneyLaundering.getRitp());
            }
            Element DETR = RBIF.addElement("DETR");
            if (antiMoneyLaundering.getDetr() != null &&
                    !antiMoneyLaundering.getDetr().equals("")) {
                DETR.setText(antiMoneyLaundering.getDetr());
            }
            Element TORP = RBIF.addElement("TORP");
            if (String.valueOf(antiMoneyLaundering.getTorp()) != null &&
                    !String.valueOf(antiMoneyLaundering.getTorp()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getTorp()).equals("null")) {
                TORP.setText(String.valueOf(antiMoneyLaundering.getTorp()));
            }
            Element ORXN = RBIF.addElement("ORXN");
            if (antiMoneyLaundering.getOrxn() != null &&
                    !antiMoneyLaundering.getOrxn().equals("")) {
                ORXN.setText(antiMoneyLaundering.getOrxn());
            }
            Element DORP = RBIF.addElement("DORP");
            if (antiMoneyLaundering.getDorp() != null &&
                    !antiMoneyLaundering.getDorp().equals("")) {
                DORP.setText(antiMoneyLaundering.getDorp());
            }
            Element ODRP = RBIF.addElement("ODRP");
            if (antiMoneyLaundering.getOdrp() != null &&
                    !antiMoneyLaundering.getOdrp().equals("")) {
                ODRP.setText(antiMoneyLaundering.getOdrp());
            }
            Element TPTR = RBIF.addElement("TPTR");
            if (antiMoneyLaundering.getTptr() != null &&
                    !antiMoneyLaundering.getTptr().equals("")) {
                TPTR.setText(antiMoneyLaundering.getTptr());
            }
            Element OTPR = RBIF.addElement("OTPR");
            if (antiMoneyLaundering.getOtpr() != null &&
                    !antiMoneyLaundering.getOtpr().equals("")) {
                OTPR.setText(antiMoneyLaundering.getOtpr());
            }
            Element STCB = RBIF.addElement("STCB");
            if (antiMoneyLaundering.getStcb() != null &&
                    !antiMoneyLaundering.getStcb().equals("")) {
                STCB.setText(antiMoneyLaundering.getStcb());
            }
            Element AOSP = RBIF.addElement("AOSP");
            if (antiMoneyLaundering.getAosp() != null &&
                    !antiMoneyLaundering.getAosp().equals("")) {
                AOSP.setText(antiMoneyLaundering.getAosp());
            }
            Element TOSCs = RBIF.addElement("TOSCs");
            Element TOSC = TOSCs.addElement("TOSC");
            TOSC.addAttribute("seqno", "1");
            if (antiMoneyLaundering.getTosc() != null &&
                    !antiMoneyLaundering.getTosc().equals("")) {
                TOSC.setText(antiMoneyLaundering.getTosc());
            }

            Element STCRs = RBIF.addElement("STCRs");
            Element STCR = STCRs.addElement("STCR");
            STCR.addAttribute("seqno", "1");
            if (antiMoneyLaundering.getStcr() != null &&
                    !antiMoneyLaundering.getStcr().equals("")) {
                STCR.setText(antiMoneyLaundering.getStcr());
            }
            Element SBDT = RBIF.addElement("SBDT");
            if (String.valueOf(antiMoneyLaundering.getSbdt()) != null &&
                    !String.valueOf(antiMoneyLaundering.getSbdt()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getSbdt()).equals("null")) {
                SBDT.setText(String.valueOf(antiMoneyLaundering.getSbdt()));
            }
            Element SEDT = RBIF.addElement("SEDT");
            if (String.valueOf(antiMoneyLaundering.getSedt()) != null &&
                    !String.valueOf(antiMoneyLaundering.getSedt()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getSedt()).equals("null")) {
                SEDT.setText(String.valueOf(antiMoneyLaundering.getSedt()));
            }
            Element ROTFs = RBIF.addElement("ROTFs");
            Element ROTF = ROTFs.addElement("ROTF");
            ROTF.addAttribute("seqno", "1");
            if (antiMoneyLaundering.getRotf() != null &&
                    !antiMoneyLaundering.getRotf().equals("")) {
                ROTF.setText(antiMoneyLaundering.getRotf());
            }


            Element SEIFs = rss.addElement("SEIFs");
            Element SEIF = SEIFs.addElement("SEIF");
            SEIF.addAttribute("seqno", "1");
            Element SEVC = SEIF.addElement("SEVC");
            if (antiMoneyLaundering.getSevc() != null &&
                    !antiMoneyLaundering.getSevc().equals("")) {
                SEVC.setText(antiMoneyLaundering.getSevc());
            }
            Element SIIFs = SEIF.addElement("SIIFs");
            Element SIIF = SIIFs.addElement("SIIF");
            SIIF.addAttribute("seqno", "1");
            Element SENM = SIIF.addElement("SENM");
            if (antiMoneyLaundering.getSenm() != null &&
                    !antiMoneyLaundering.getSenm().equals("")) {
                SENM.setText(antiMoneyLaundering.getSenm());
            }
            Element SETP = SIIF.addElement("SETP");
            if (antiMoneyLaundering.getSetp() != null &&
                    !antiMoneyLaundering.getSetp().equals("")) {
                SETP.setText(antiMoneyLaundering.getSetp());
            }
            Element OITP = SIIF.addElement("OITP");
            if (antiMoneyLaundering.getOitp() != null &&
                    !antiMoneyLaundering.getOitp().equals("")) {
                OITP.setText(antiMoneyLaundering.getOitp());
            }
            Element SEID = SIIF.addElement("SEID");
            if (String.valueOf(antiMoneyLaundering.getSeid()) != null &&
                    !String.valueOf(antiMoneyLaundering.getSeid()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getSeid()).equals("null")) {
                SEID.setText(String.valueOf(antiMoneyLaundering.getSeid()));
            }
            Element STNTs = SEIF.addElement("STNTs");
            Element STNT = STNTs.addElement("STNT");
            STNT.addAttribute("seqno", "1");
            if (antiMoneyLaundering.getStnt() != null &&
                    !antiMoneyLaundering.getStnt().equals("")) {
                STNT.setText(antiMoneyLaundering.getStnt());
            }
            Element SCIF = SEIF.addElement("SCIF");
            if (antiMoneyLaundering.getScif() != null &&
                    !antiMoneyLaundering.getScif().equals("")) {
                SCIF.setText(antiMoneyLaundering.getScif());
            }
            Element SRIF = SEIF.addElement("SRIF");
            Element SRNM = SRIF.addElement("SRNM");
            if (antiMoneyLaundering.getSrnm() != null &&
                    !antiMoneyLaundering.getSrnm().equals("")) {
                SRNM.setText(antiMoneyLaundering.getSrnm());
            }
            Element SRIT = SRIF.addElement("SRIT");
            if (antiMoneyLaundering.getSrit() != null &&
                    !antiMoneyLaundering.getSrit().equals("")) {
                SRIT.setText(antiMoneyLaundering.getSrit());
            }
            Element ORIT = SRIF.addElement("ORIT");
            if (antiMoneyLaundering.getOrit() != null &&
                    !antiMoneyLaundering.getOrit().equals("")) {
                ORIT.setText(antiMoneyLaundering.getOrit());
            }
            Element SRID = SRIF.addElement("SRID");
            if (String.valueOf(antiMoneyLaundering.getSrid()) != null &&
                    !String.valueOf(antiMoneyLaundering.getSrid()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getSrid()).equals("null")) {
                SRID.setText(String.valueOf(antiMoneyLaundering.getSrid()));
            }
            Element SCNM = SRIF.addElement("SCNM");
            if (antiMoneyLaundering.getScnm() != null &&
                    !antiMoneyLaundering.getScnm().equals("")) {
                SCNM.setText(antiMoneyLaundering.getScnm());
            }
            Element SCIT = SRIF.addElement("SCIT");
            if (antiMoneyLaundering.getScit() != null &&
                    !antiMoneyLaundering.getScit().equals("")) {
                SCIT.setText(antiMoneyLaundering.getScit());
            }
            Element OCIT = SRIF.addElement("OCIT");
            if (antiMoneyLaundering.getOcit() != null &&
                    !antiMoneyLaundering.getOcit().equals("")) {
                OCIT.setText(antiMoneyLaundering.getOcit());
            }
            Element SCID = SRIF.addElement("SCID");
            if (String.valueOf(antiMoneyLaundering.getScid()) != null &&
                    !String.valueOf(antiMoneyLaundering.getScid()).equals("") &&
                    !String.valueOf(antiMoneyLaundering.getScid()).equals("null")) {
                SCID.setText(String.valueOf(antiMoneyLaundering.getScid()));
            }
            Element SCAIs = SEIF.addElement("SCAIs");
            Element SCAI = SCAIs.addElement("SCAI");
            SCAI.addAttribute("seqno", "1");
            Element SCBA = SCAIs.addElement("SCBA");
            if (antiMoneyLaundering.getScba() != null &&
                    !antiMoneyLaundering.getScba().equals("")) {
                SCBA.setText(antiMoneyLaundering.getScba());
            }
            Element SCBN = SCAIs.addElement("SCBN");
            if (antiMoneyLaundering.getScbn() != null &&
                    !antiMoneyLaundering.getScbn().equals("")) {
                SCBN.setText(antiMoneyLaundering.getScbn());
            }


            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");


            // 6、生成xml文件
            File file = new File("antiMoneyLaunderingXML/" + antiMoneyLaundering.getAntiId() + ".xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成rss.xml成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成rss.xml失败");
            return false;
        }
    }

}
