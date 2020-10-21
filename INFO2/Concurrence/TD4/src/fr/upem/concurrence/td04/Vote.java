package fr.upem.concurrence.td04;

import java.util.HashMap;
import java.util.Map;

public class Vote {
    private final int voteNumber;
    private int totalVote = 0;
    private final Map<String, Integer> votes;
    private final Object lock = new Object();

    public Vote(int voteNumber) {
        if(voteNumber < 0) {
            throw new IllegalArgumentException("There must be at least 0 vote");
        }

        this.voteNumber = voteNumber;
        votes = new HashMap<>();
    }

    private String getWinner() {
        return votes.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow()
            .getKey();
    }

    public String vote(String vote) throws InterruptedException {
        synchronized (lock) {
            Integer nbVotes = votes.putIfAbsent(vote, 1);
            if(nbVotes != null) {
                votes.replace(vote, nbVotes + 1);
            }

            totalVote++;
            lock.notifyAll();
            while (totalVote < voteNumber) {
                lock.wait();
            }

            return getWinner();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Vote vote = new Vote(3);
        new Thread(
                () -> {
                    try {
                        Thread.sleep(1_000);
                        var winner =  vote.vote("1");
                        System.out.println("The winner is " + winner);
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        new Thread(
                () -> {
                    try {
                        Thread.sleep(5_000);
                        System.out.println("The winner is " + vote.vote("0"));
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
                    }
                })
                .start();
        System.out.println("The winner is " + vote.vote("0"));
    }
}
