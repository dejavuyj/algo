package tools.music;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.Entry.comparingByValue;

public class MusicAnalyze {

    public static void main(String[] args) throws Exception {
        String filePath = "tools/music/music_info"; // 文件路径
        Pattern musicPattern = Pattern.compile("data-res-name=\"(.*?)\"");
        Pattern authorPattern = Pattern.compile("data-res-author=\"(.*?)\"");

        Map<String, Integer> authorMap = new HashMap<>();
        List<String> authorList = new ArrayList<>();
        Map<String, Integer> musicMap = new HashMap<>();
        List<String> musicList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher authorMatcher = authorPattern.matcher(line);
                while (authorMatcher.find()) {
                    String author = authorMatcher.group(1);
                    author = author.replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
                    Integer cnt = authorMap.getOrDefault(author, 0);
                    authorMap.put(author, cnt+1);
                    authorList.add(author);
                }

                Matcher musicMatcher = musicPattern.matcher(line);
                while (musicMatcher.find()) {
                    String music = musicMatcher.group(1);
                    music = music.replaceAll("&amp;", "&").replaceAll("&quot;", "'");
                    Integer cnt = musicMap.getOrDefault(music, 0);
                    musicMap.put(music, cnt+1);
                    musicList.add(music);
                }
            }
        }

        Map<String, Integer> countSortAuthorMap = new LinkedHashMap<>();
        authorMap.entrySet().stream().sorted(Collections.reverseOrder(comparingByValue())).forEachOrdered(e -> countSortAuthorMap.put(e.getKey(), e.getValue()));
        Map<String, Integer> countSortMusicMap = new LinkedHashMap<>();
        musicMap.entrySet().stream().sorted(Collections.reverseOrder(comparingByValue())).forEachOrdered(e -> countSortMusicMap.put(e.getKey(), e.getValue()));
//        System.out.println(JSON.toJSONString(countSortAuthorMap));
//        System.out.println(JSON.toJSONString(countSortMusicMap));

        Map<String, List<String>> authorMusicMap = new HashMap<>();
        StringBuilder prompt = new StringBuilder();
//        prompt.append("以下是我在喜欢并收藏的作者和歌曲, 请根据歌曲统计不同风格的数量\n");
        prompt.append("以下是我在喜欢并收藏的作者和歌曲, 请根据歌曲的名称与演唱艺人信息，专业、深刻且幽默风趣地锐评下我的听歌品味，并解析下我的内心世界\n");
        for (int i = 0; i < authorList.size(); i++) {
            String author = authorList.get(i);
            String music = musicList.get(i);
            prompt.append(author).append(" ").append(music).append(";");
            List<String> curAuthorMusicList = authorMusicMap.getOrDefault(author, new ArrayList<>());
            curAuthorMusicList.add(musicList.get(i));
            authorMusicMap.put(author, curAuthorMusicList);
        }
        Map<String, List<String>> countSortAuthorMusicMap = new LinkedHashMap<>();
        authorMusicMap.entrySet().stream().sorted(Collections.reverseOrder(Comparator.comparingInt(o -> o.getValue().size()))).forEachOrdered(e -> {
                if (e.getValue().size() >= 5) {
                    countSortAuthorMusicMap.put(e.getKey(), e.getValue());
                }
        });
//        System.out.println(JSON.toJSONString(countSortAuthorMusicMap, SerializerFeature.PrettyFormat));
        System.out.println(prompt.toString());
    }
}