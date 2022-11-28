package com.qyh.decision_tree.utils;

import java.io.*;
import java.util.List;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 文件操作工具类
 * @date 2022年11月28日20:42:43
 */

public class FileUtils {

    /*
     * @author: K0n9D1KuA
     * @description: 将一些字符追加到文件末尾
     * @param: null
     * @return:
     * @date: 2022/11/28 20:45
     */

    public static void appendToExitingFile(List<String> filterTypes) throws IOException {
        BufferedReader br = null;
        PrintWriter ps = null;
        int i = -1;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("DecisionTreeConstantOrigin.java").getAbsoluteFile())));
            ps = new PrintWriter(new FileOutputStream(new File("DecisionTreeConstant.java")));
            while ((i = br.read()) != -1) {
                ps.write(i);
            }
            for (String filterType : filterTypes
            ) {
                String upperOne = filterType.toUpperCase();
                String originOne = filterType.toLowerCase();
                ps.write("    public static String " + upperOne + "_DECISION = \"" + originOne + "\";\n");
            }
            ps.write("}");
        } finally {
            if (br != null) {
                br.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

    }

    /*
     * @author: K0n9D1KuA
     * @description: 批量生成filter文件
     * @param: null
     * @return:
     * @date: 2022/11/28 20:46
     */

    public static void replace(List<String> filterType) throws Exception {

        for (String fT : filterType) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("LogicFilter.java").getAbsoluteFile())));
            PrintWriter ps = new PrintWriter(new FileOutputStream(new File(fT + "LogicFilter" + ".java")));
            int i = -1, count = 0;
            while ((i = br.read()) != -1) {
                if ((char) i != '?')
                    ps.write(i);
                else {
                    if (count == 0) {
                        ps.write(fT.toCharArray());
                        count++;
                    } else {
                        ps.write(fT.toUpperCase().toCharArray());
                    }
                }
            }
            br.close();
            ps.close();
        }

    }
}
