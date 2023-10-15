package tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Travel {

    private static int levelNum = 20;
    private static String rootName = "中国旅行";
    private static JSONObject jo = new JSONObject();
    private static int[] levelCounts = new int[levelNum];
    private static List<Stack<String>> levelStackList = new ArrayList<>();
    private static Map<String, List<String>> prefecturalMap = new HashMap<>();
    private static Map<Integer, Map<String, List<String>>> nodeMap = new HashMap<>();
    private static Set<String> specialLevelOneNodeSet = new HashSet<>(Arrays.asList("北京", "上海", "天津", "重庆", "香港", "澳门", "台湾"));
    private static ProvinceDirectlyControlCounties henan = ProvinceDirectlyControlCounties.builder().province("河南").counties(Arrays.asList("济源市")).build();
    private static ProvinceDirectlyControlCounties hubei = ProvinceDirectlyControlCounties.builder().province("湖北").counties(Arrays.asList("仙桃市", "潜江市", "天门市", "神农架林区")).build();
    private static ProvinceDirectlyControlCounties hainan = ProvinceDirectlyControlCounties.builder().province("海南").counties(Arrays.asList("五指山市", "文昌市", "琼海市", "万宁市", "东方市", "定安县", "屯昌县", "澄迈县", "临高县", "琼中黎族苗族自治县", "保亭黎族苗族自治县", "白沙黎族自治县", "昌江黎族自治县", "乐东黎族自治县", "陵水黎族自治县")).build();
    private static ProvinceDirectlyControlCounties xinjiang = ProvinceDirectlyControlCounties.builder().province("新疆维吾尔自治区").counties(Arrays.asList("石河子市", "阿拉尔市", "图木舒克市", "五家渠市", "北屯市", "铁门关市", "双河市", "可克达拉市", "昆玉市", "胡杨河市", "新星市", "白杨市")).build();
    private static List<ProvinceDirectlyControlCounties> provinceDirectlyControlCountiesList = Arrays.asList(henan, hubei, hainan, xinjiang);
    private static Set<String> provinceDirectlyControlCountiesNameSet = provinceDirectlyControlCountiesList.stream().flatMap(pdc -> {
        String province = pdc.getProvince();
        return pdc.getCounties().stream().map(c -> province + "-" + c);
    }).collect(Collectors.toSet());

    public static int search(String str, String strRes) {
        int n = 0;//计数器
        int index = 0;//指定字符的长度
        index = str.indexOf(strRes);
        while (index != -1) {
            n++;
            index = str.indexOf(strRes, index + 1);
        }

        return n;
    }

    @Data
    @Builder
    private static class ProvinceDirectlyControlCounties {
        String province;
        List<String> counties;
    }

    private static void collectNodes() throws IOException {
        jo.put(rootName, new JSONObject());
        for (int i = 0; i < levelNum; i++) {
            levelStackList.add(new Stack<>());
        }
        List<String> nodes = Files.readAllLines(Paths.get("tools/TravelNodes"), StandardCharsets.UTF_8);
        for (String s : nodes) {
            int level = search(s, "\t");
            levelCounts[level]++;

//            if (level == 0) System.out.println(s);

            String currentNodeName = s.replaceAll("\t", "");
            Stack<String> currentLevelStack = levelStackList.get(level);
            if (level > 0 || currentNodeName.equals(rootName)) {
                currentLevelStack.push(currentNodeName);
            }

            JSONObject parent = jo;
            for (int i = 0; i < level && parent != null; i++) {
                String levelIName = levelStackList.get(i).peek();
                parent = parent.getJSONObject(levelIName);
            }
            if (parent != null && level > 0) {
                parent.put(currentNodeName, new JSONObject());
            }
        }
    }

    private static void recordToMap() {
        recordToMap(1, "", jo.getJSONObject(rootName));
    }

    private static void recordToMap(int level, String parentNodeName, JSONObject jo) {
        if (jo == null || jo.values().size() == 0) {
            return;
        }

        Map<String, List<String>> currentLevelSubMap = nodeMap.getOrDefault(level, new HashMap<>());
        nodeMap.put(level, currentLevelSubMap);

        for (Map.Entry<String, Object> entry0 : jo.entrySet()) {
            String connector = "".equals(parentNodeName) ? "" : "-";
            String currentNodeName = parentNodeName + connector + entry0.getKey();

            JSONObject o1 = (JSONObject) entry0.getValue();

            List<String> currentNodeSubList = currentLevelSubMap.getOrDefault(currentNodeName, new ArrayList<>());
            currentLevelSubMap.put(currentNodeName, currentNodeSubList);

            for (Map.Entry<String, Object> entry1 : o1.entrySet()) {
                String subNodeName = entry1.getKey();
                currentNodeSubList.add(subNodeName);
            }

            recordToMap(level + 1, currentNodeName, o1);
        }
    }

    private static void printLevels() {
        List<JSONObject> specialLevelOneNodesList = jo.getJSONObject(rootName).entrySet().stream()
                .filter(e -> specialLevelOneNodeSet.contains(e.getKey()))
                .map(e -> JSON.parseObject(JSON.toJSONString(e))).collect(Collectors.toList());
        int specialLevelOneSubNodeNums = specialLevelOneNodesList.stream().map(JSONObject::values).map(Collection::toArray).map(a -> a[0]).map(e -> (JSONObject) e).mapToInt(JSONObject::size).sum();

        int totalCount = 1;
//        int printLevelNum = 5;
        int printLevelNum = levelNum;
        for (int i = 1; i < printLevelNum; i++) {
            if (levelCounts[i] != 0) {
                String explain = i + "级节点 " + levelCounts[i] + " 个";
                if (i == 1) {
                    explain += ",  包括23个省、5个自治区、4个直辖市、2个特别行政区";
                } else if (i == 2) {
                    explain += ", 包括333个地级区划, 32个省直辖县级行政单位, 北京/上海/天津/重庆 四个直辖市, 以及港澳台的" + specialLevelOneSubNodeNums + "个子节点";
                } else if (i == 3) {
                    explain += ", 包括 北京/上海/天津/重庆 四个直辖市, 以及港澳台的孙节点";
                } else if (i == 4) {
                    explain += ", 大部分是景点";
                }
                System.out.println(explain);
                totalCount += levelCounts[i];
            }
        }
        System.out.println("总节点数: " + totalCount + "\n");

        // 各level重名节点
        List<Set<String>> levelSetList = new ArrayList<>();
        for (Stack<String> stack : levelStackList) {
            Set<String> set = new HashSet<>();
            Enumeration<String> stackElements = stack.elements();
            while (stackElements.hasMoreElements()) {
                set.add(stackElements.nextElement());
            }
            levelSetList.add(set);
        }
    }

    private static void printSubCnt() {
        // 333个地级行政区划, 二级节点, 去除32个省直辖级行政单位, 北京/上海/天津/重庆 四个直辖市以及港澳台的下级节点
        nodeMap.get(2).forEach((k, v) -> {
            String levelOneName = k.substring(0, k.indexOf("-"));
            String prefecturalName = k.substring(k.indexOf("-") + 1);
            if (!specialLevelOneNodeSet.contains(levelOneName) && !provinceDirectlyControlCountiesNameSet.contains(k)) {
//                prefecturalMap.put(k, v);
                prefecturalMap.put(prefecturalName, v);
            }
        });

        System.out.println("333个地级区划, 按子节点数量统计:");
        printTable(prefecturalMap);
    }

    private static void printTable(Map<String, List<String>> map) {
        List<String> subCntHead = new ArrayList<>();
        List<String> subCntData = new ArrayList<>();

        long[] levelTwoSubCountArray = new long[30];
        for (int i = 0; i < 30; i++) {
            final int fSubCountNum = i;
            levelTwoSubCountArray[i] = map.entrySet().stream().filter(e -> e.getValue().size() == fSubCountNum).count();

            if (levelTwoSubCountArray[i] != 0) {
                subCntHead.add(String.valueOf(i));
                subCntData.add(String.valueOf(levelTwoSubCountArray[i]));
            }
        }
        List<Map.Entry<String, List<String>>> sortedlist = new ArrayList<>(map.entrySet());
        sortedlist.sort((o1, o2) -> -(o2.getValue().size() - (o1.getValue().size())));

        PrintTable.printTable(subCntHead, subCntData);
    }

    private static void printPercent() {
        // 直辖市 + 333个地级市
        Map<String, List<String>> centralDirectlyControlCityAndPrefecturalMap = new HashMap<>();
        nodeMap.get(1).forEach((k, v) -> {
            if (AdministrativeDivision.centralDirectlyControlCitySet.contains(k)) {
                prefecturalMap.put(k, v);
            }
        });
        centralDirectlyControlCityAndPrefecturalMap.putAll(prefecturalMap);

        Map<String, Double> prefecturalCompletePercentageMap = new HashMap<>();
        // 实际管辖数量
        Map<String, Integer> prefecturalSubNumMap = AdministrativeDivision.getPrefecturalSubNumMap();
        prefecturalSubNumMap.forEach((k, v) -> {
            List<String> recordedList = centralDirectlyControlCityAndPrefecturalMap.get(k);
            if (recordedList == null) {
                System.out.println(k + ", 没有被记录");
                return;
            }
            double percent = v == 0 ? 1 : (double) recordedList.size() / v;
            percent = Math.min(percent, 1);
            prefecturalCompletePercentageMap.put(k, percent);
        });
        List<Map.Entry<String, Double>> sortedPrefecturalCompletePercentagelist = new ArrayList<>(prefecturalCompletePercentageMap.entrySet());
        sortedPrefecturalCompletePercentagelist.sort((o1, o2) -> -o2.getValue().compareTo(o1.getValue()));

        Map<String, Long> percentMap = sortedPrefecturalCompletePercentagelist.stream().mapToDouble(Map.Entry::getValue).mapToObj(p -> String.format("%.1f", p)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        TreeMap<String, Long> sortedPercentMap = new TreeMap<>(percentMap);
        List<String> percentCntHead = new ArrayList<>();
        List<String> percentCntData = new ArrayList<>();
        sortedPercentMap.forEach((k, v) -> {
            percentCntHead.add(k);
            percentCntData.add(v.toString());
        });

        System.out.println("333个地级区划加4个直辖市, 按子节点完成率统计:");
        PrintTable.printTable(percentCntHead, percentCntData);
    }

    private static void printCounties() {
        // 统计县级行政区的子节点数量, 不包括港澳台
        String[] countyTypes = {"区", "县", "市", "自治县", "旗", "自治旗", "特区", "林区"};
        int[] countyTypeTotalNums = {977, 1299, 397, 117, 49, 3, 1, 1};
        int[] countyTypeCollectNums = {0, 0, 0, 0, 0, 3, 1, 1};
        int[] countyTypeLackNums = {0, 0, 0, 0, 0, 3, 1, 1};
        Set<String> specialCounties = new HashSet<>(Arrays.asList("莫力达瓦达斡尔族自治旗", "鄂伦春自治旗", "鄂温克族自治旗", "六枝特区", "神农架林区"));
        Map<String, List<String>> countiesMap = new HashMap<>();
        nodeMap.get(2).forEach((k, v) -> {
            // 直辖市的区县
            // 省直辖县级行政单位
            String levelOneName = k.substring(0, k.indexOf("-"));
            if (AdministrativeDivision.centralDirectlyControlCitySet.contains(levelOneName)
                    || provinceDirectlyControlCountiesNameSet.contains(k)) {
                countiesMap.put(k, v);
                String countyName = k.split("-")[1];
                if (specialCounties.contains(countyName)) {
                    return;
                }

                for (int i = 4; i >= 0; i--) {
                    if (countyName.endsWith(countyTypes[i])) {
                        countyTypeCollectNums[i]++;
                        return;
                    }
                }
            }
        });

        // 县级行政区
        nodeMap.get(3).forEach((k, v) -> {
            // 跳过直辖市, 港澳台, 省直辖县级行政单位
            String levelOneName = k.substring(0, k.indexOf("-"));
            String levelTwoName = k.substring(0, k.lastIndexOf("-"));
            if (specialLevelOneNodeSet.contains(levelOneName) || provinceDirectlyControlCountiesNameSet.contains(levelTwoName)) {
                return;
            }

            String countyName = k.split("-")[2];
            if (specialCounties.contains(countyName)) {
                countiesMap.put(k, v);
                return;
            }

            for (int i = 4; i >= 0; i--) {
                if (countyName.endsWith(countyTypes[i])) {
                    countyTypeCollectNums[i]++;
                    countiesMap.put(k, v);
                    return;
                }
            }
        });
        int collectNums = Arrays.stream(countyTypeCollectNums).sum();

//        for (int i = 0; i < countyTypeTotalNums.length; i++) {
//            countyTypeLackNums[i] = countyTypeTotalNums[i] - countyTypeCollectNums[i];
//        }
//        System.out.printf("中国有 %3d个市辖区, %4d个县, %3d个县级市, %3d个自治县, %2d个旗, %d个自治旗, %d个特区, %d个林区, 合计 %4d个县级行政区\n",
//                countyTypeTotalNums[0], countyTypeTotalNums[1], countyTypeTotalNums[2], countyTypeTotalNums[3], countyTypeTotalNums[4], countyTypeTotalNums[5], countyTypeTotalNums[6], countyTypeTotalNums[7], Arrays.stream(countyTypeTotalNums).sum());
//        System.out.printf("已收集 %3d个市辖区, %4d个县, %3d个县级市, %3d个自治县, %2d个旗, %d个自治旗, %d个特区, %d个林区, 合计 %4d个县级行政区\n",
//                countyTypeCollectNums[0], countyTypeCollectNums[1], countyTypeCollectNums[2], countyTypeCollectNums[3], countyTypeCollectNums[4], countyTypeCollectNums[5], countyTypeCollectNums[6], countyTypeCollectNums[7], collectNums);
//        System.out.printf("还缺少 %3d个市辖区, %4d个县, %3d个县级市, %3d个自治县, %2d个旗, %d个自治旗, %d个特区, %d个林区, 合计 %4d个县级行政区\n",
//                countyTypeLackNums[0], countyTypeLackNums[1], countyTypeLackNums[2], countyTypeLackNums[3], countyTypeLackNums[4], countyTypeLackNums[5], countyTypeLackNums[6], countyTypeLackNums[7], Arrays.stream(countyTypeLackNums).sum());

//        List<String> cl = countiesMap.keySet().stream().filter(
////                c -> c.endsWith("区")
////                c -> !c.endsWith("自治县") && c.endsWith("县")
////                c -> c.endsWith("市")
////                c -> c.endsWith("自治县")
//                c -> !c.endsWith("自治旗") && c.endsWith("旗")
//        ).map(c -> c.substring(c.lastIndexOf("-") + 1)).sorted(
//                (name1, name2) -> {
//                    Collator instance = Collator.getInstance(Locale.CHINA);
//                    return instance.compare(name1, name2);
//                }).collect(Collectors.toList());
//        System.out.println();
//        cl.forEach(System.out::println);

        System.out.println(collectNums + "个县级区划, 按子节点数量统计:");
        printTable(countiesMap);
    }

    private static boolean isNormalNode(String nodeName) {
        return !nodeName.contains("美食");
    }

    public static void main(String[] args) throws IOException {
        collectNodes();
        recordToMap();
        printLevels(); // 各层级节点数
//        printSubCnt(); // 地级市子节点数量
//        printPercent(); // 地级市+直辖区, 完成率
        printCounties(); // 县级统计
    }
}
