package models;

import org.sql2o.Connection;

import java.util.List;

public class Rangers {
    private int id;
    private String rangerName;
    private int badgeNo;

    public Rangers(String rangerName,int badgeNo){
        this.rangerName = rangerName;
        this.badgeNo = badgeNo;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getId() {
        return id;
    }

    public int getBadgeNo() {
        return badgeNo;
    }


    @Override
    public boolean equals(Object otherRangers) {
        if (this == otherRangers) return true;
        if (!(otherRangers instanceof Rangers)) return false;

        Rangers newRanger = (Rangers) otherRangers;

        if (getId() != newRanger.getId()) return false;
        if (getBadgeNo() != newRanger.getBadgeNo()) return false;
        return getRangerName().equals(newRanger.getRangerName());
    }

    public void save(){
        try (Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO rangers(rangerName,badgeNo) VALUES(:rangerName,:badgeNo)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("rangerName",rangerName)
                    .addParameter("badgeNo",this.badgeNo)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Rangers> all(){
        try(Connection con = DB.sql2o.open()){
            return  con.createQuery("SELECT * FROM  rangers")
                    .executeAndFetch(Rangers.class);
        }
    }

    public static Rangers find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM rangers WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Rangers.class);
        }

    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
