package com.mtax.dm.controller;


import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.google.common.collect.Maps;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
@RestController
public class test {
    @GetMapping("/test")
    public void test() throws Exception {


        HashMap<String, Object> map = Maps.newHashMap();
        map.put("pointCode","1234");
        map.put("hasSampling1", "是");
        map.put("hasSampling2", "是");
//        String path = this.getClass().getResource("/templates/template.docx").getPath();
//        System.out.println(path);
        XWPFDocument xwpfDocument = WordExportUtil.exportWord07("src/main/resources/templates/template.docx", map);
        FileOutputStream fos = new FileOutputStream("d:/chen.docx");
        xwpfDocument.write(fos);
        fos.close();
    }
}
