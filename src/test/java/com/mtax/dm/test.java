package com.mtax.dm;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.google.common.collect.Maps;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Test
    public void imageWordExport () throws Exception {
        try {
            String path = new ClassPathResource("templates/template.docx").getUrl().getPath();
            System.out.println(path);
//            HashMap<String, Object> map = Maps.newHashMap();
//            map.put("pointCode","1234");
//            map.put("hasSampling1", "是");
//            map.put("hasSampling2", "是");
//            XWPFDocument doc = WordExportUtil.exportWord07(
//                    "classpath:/templates/template.docx", map);
//            FileOutputStream fos = new FileOutputStream("D:/eeee.docx");
//            doc.write(fos);
//            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
