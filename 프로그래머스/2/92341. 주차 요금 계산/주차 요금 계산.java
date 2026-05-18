import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Map<String, Integer> map1 = new HashMap<>(); // 번호 : 입차시간
        Map<String, Integer> map2 = new HashMap<>(); // 번호 : 누적합
        
        for(String record: records) {
            // info -> [0]시각, [1]차량번호, [2]내역
            String[] info = record.split(" ");
            int time = getMinute(info[0].split(":"));
            String key = info[1];
            if(info[2].equals("IN")) {
                map1.put(key, time);
            } else {
                int inTime = map1.get(key);
                map1.remove(key);
                map2.put(key, map2.getOrDefault(key, 0) + time - inTime);
            }
        }
        int maxTime = 23 * 60 + 59;
        
        while(!map1.isEmpty()) {
            String key = map1.keySet().iterator().next();
            map2.put(key, map2.getOrDefault(key, 0) + maxTime - map1.get(key));
            map1.remove(key);
        }
        
        ArrayList<String> keys = new ArrayList<>(map2.keySet());
        Collections.sort(keys);
        
        int[] answer = new int[keys.size()];
        
        for(int i = 0; i < keys.size(); i++) {
            int time = map2.get(keys.get(i));
            answer[i] = fees[1];
            if(time > fees[0]) {
                time -= fees[0];
                if(time % fees[2] == 0) {
                    answer[i] += fees[3] * (time/fees[2]);
                } else {
                    answer[i] += fees[3] * (time/fees[2]) + fees[3];
                }
            }
        }
        
        return answer;
    }
    
    public int getMinute(String[] time) {
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        return hour * 60 + minute;
    }
}