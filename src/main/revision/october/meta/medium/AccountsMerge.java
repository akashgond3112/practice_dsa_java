package main.revision.october.meta.medium;

import java.util.*;

public class AccountsMerge {

    class Solution {
        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            // build email index
            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 1; acc < accountList.size(); acc++) {

                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emails.add(curEmail);
                        emailIdx.put(curEmail, m);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            // Build Adj list
            adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            // dfs
            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int acctid = emailToAcc.get(i);
                    emailGroup.putIfAbsent(acctid, new ArrayList<>());
                    dfs(i, acctid);
                }
            }

            // build result
            List<List<String>> result = new ArrayList<>();
            for (int acctId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(acctId);
                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(acctId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;
            emailGroup.get(acctId).add(emails.get(node));

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, acctId);
                }
            }
        }
    }

    // revised on 11/26/2025
    class SolutionRevisonOnthirdDay {
        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 1; acc < accountList.size(); acc++) {

                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emails.add(curEmail);
                        emailIdx.put(curEmail, m);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int accid = emailToAcc.get(i);
                    emailGroup.putIfAbsent(accid, new ArrayList<>());
                    dfs(i, accid);
                }
            }

            List<List<String>> result = new ArrayList<>();

            for (int accId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(accId);

                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(accId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;

            emailGroup.get(acctId).add(emails.get(node));

            for (int nei : adj.get(node)) {
                if (!visited[nei])
                    dfs(nei, acctId);
            }
        }
    }

    class SolutionRevisonOnSeventhDay {
        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            // build eamil index
            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 0; acc < accountList.size(); acc++) {
                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emails.add(curEmail);
                        emailIdx.put(curEmail, m);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            // build adj list

            adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            visited = new boolean[m];

            // dfs
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int acctId = emailToAcc.get(i);
                    emailGroup.putIfAbsent(acctId, new ArrayList<>());
                    dfs(i, acctId);
                }
            }

            // build result
            List<List<String>> result = new ArrayList<>();
            for (int acctId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(acctId);
                Collections.sort(group);

                List<String> merged = new ArrayList<>();
                merged.add(accounts.get(acctId).get(0));
                merged.addAll(group);
                result.add(merged);
            }

            return result;
        }

        private void dfs(int node, int acctId) {

            visited[acctId] = true;
            emailGroup.get(acctId).add(emails.get(node));

            for (int nei : adj.get(node)) {
                if (!visited[nei]) {
                    dfs(nei, acctId);
                }
            }
        }
    }

    // Revised on 12/16/2025
    class SolutionRevisonOnFourtenDay {

        private Map<String, Integer> emailIdx = new HashMap<>();
        private List<String> emails = new ArrayList<>();
        private Map<Integer, Integer> emailToAcc = new HashMap<>();
        private List<List<Integer>> adj;
        private Map<Integer, List<String>> emailGroup = new HashMap<>();
        private boolean[] visited;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int m = 0;

            for (int index = 0; index < n; index++) {
                List<String> accountList = accounts.get(index);

                for (int acc = 0; acc < accountList.size(); acc++) {
                    String curEmail = accountList.get(acc);

                    if (!emailIdx.containsKey(curEmail)) {
                        emailIdx.put(curEmail, m);
                        emails.add(curEmail);
                        emailToAcc.put(m, index);
                        m++;
                    }
                }
            }

            this.adj = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                adj.add(new ArrayList<>());
            }

            for (List<String> acc : accounts) {
                for (int i = 2; i < acc.size(); i++) {
                    int idx1 = emailIdx.get(acc.get(i));
                    int idx2 = emailIdx.get(acc.get(i - 1));

                    adj.get(idx1).add(idx2);
                    adj.get(idx2).add(idx1);
                }
            }

            this.visited = new boolean[m];

            for (int i = 0; i < m; i++) {
                if (!visited[i]) {
                    int accId = emailToAcc.get(i);
                    emailGroup.putIfAbsent(accId, new ArrayList<>());
                    dfs(i, accId);
                }
            }

            List<List<String>> result = new ArrayList<>();
            for (int accId : emailGroup.keySet()) {
                List<String> group = emailGroup.get(accId);
                Collections.sort(group);

                List<String> merge = new ArrayList<>();
                merge.add(accounts.get(accId).get(0));
                merge.addAll(group);
                result.add(merge);
            }

            return result;
        }

        private void dfs(int node, int acctId) {
            visited[acctId] = true;
            emailGroup.get(acctId).add(emails.get(node));

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, acctId);
                }
            }
        }
    }
}
