package net.hubgame.bansystem.utils;

public enum BanReason {

    KILLAURA(1, 1000L * 60L * 60L * 24L * 30L, "Hacking | Killaura", BanType.NETWORK),
    ANTIKNOCKBACK(2, 1000L * 60L * 60L * 24L * 15L, "Hacking | AntiKnockback", BanType.NETWORK),
    HACKING_ANDERES(3, 1000L * 60L * 60L * 24L * 30L, "Hacking", BanType.NETWORK),
    WERBUNG(4, 1000L * 60L * 60L * 24L * 7L, "Werbung", BanType.MUTE),
    BELEIDIGUNG(5, 1000L * 60L * 60L * 24L * 15L, "Beleidigung", BanType.MUTE),
    SPAMMING(6, 1000L * 60L * 60L * 5L, "Spamming", BanType.MUTE),
    TEAMING(7, 1000L * 60L * 60L * 24L * 5L, "Unerlaubtes Teaming", BanType.NETWORK),
    BUGUSING(8, -1L, "Bugusing", BanType.NETWORK),
    BANUMGEHUNG(9, -1L, "Banumgehung", BanType.NETWORK),
    ADMINBAN(99, -1L, "BAN EINES ADMINS", BanType.NETWORK),
    TEST(10, (1000L * 60L) + (1000L * 30L), "Test Ban", BanType.NETWORK),
    ADMINMUTE(88, -1, "MUTE EINES ADMINS", BanType.MUTE);

    private final int id;
    private long time;
    private String reason;
    private BanType banType;

    private BanReason(int id, long time, String reason, BanType banType) {
        this.id = id;
        this.time = time;
        this.reason = reason;
        this.banType = banType;
    }

    public int getID() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public BanType getBanType() {
        return banType;
    }

    public static BanReason getBanReason(int id) {
        for (BanReason reason : BanReason.values()) {
            if (reason.getID() == id)
                return reason;
        }
        return null;
    }
}
