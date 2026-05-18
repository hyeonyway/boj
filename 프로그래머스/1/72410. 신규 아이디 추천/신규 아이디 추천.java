import java.util.*;

class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9_\\-.]", "");
        new_id = new_id.replaceAll("\\.+", ".");
        new_id = new_id.replaceAll("^\\.+|\\.+$", "");
        if(new_id == null || new_id.isEmpty()) {
            new_id = "a";
        }
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("\\.+$", "");
        }
        
        if(new_id.length() <= 2) {
            StringBuilder sb = new StringBuilder(new_id);
            char last = new_id.charAt(new_id.length() - 1);
            
            while(sb.length() < 3) {
                sb.append(last);
            }
            
            new_id = sb.toString();
        }
        
        return new_id;
    }
}