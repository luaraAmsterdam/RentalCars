///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package domain.mainpackage;
//
//import db.DB;
//import db.DbCar;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import logicLevel.person.Insurer;
//import logicLevel.person.Owner;
//
///**
// *
// * @author Laura
// */
//public class Main {
//
//    public static void main(String[] args) throws IOException, SQLException {
//        Connection c = DB.getConnection();
//        
////        
////        PreparedStatement st = c.prepareStatement("INSERT INTO INSURANCE (COST, TYPE, ID_INSURER) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
////        st.setFloat(1, 100050.0f);
////        st.setString(2, "All");
////        st.setInt(3, 5);
////        st.execute();
////        ResultSet rs = st.getGeneratedKeys();
////        if (rs.next()) {
////            System.out.println(rs.getInt(1));
////        }
////        
//        System.out.println("NEW ONE");
//        
//        PreparedStatement st = c.prepareStatement("SELECT * FROM Person");
//        ResultSet res = st.executeQuery();
//        while (res.next()) {
//            System.out.println(res.getString("ID"));
//        }
//        c.close();
//
////UPDATE OR 
///*                String str = "INSERT INTO INSURANCE (COST, TYPE, ID_INSURER) VALUES (?, ?, ?)"; // matching(cost, type, id_insurer)
//         try {
//         PreparedStatement ps = c.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
//          
//         //            ps.setInt(1, -1);
//         ps.setFloat(1, 100050.0f);
//         ps.setString(2, "All");
//         ps.setInt(3, 5);
//            
//         ps.execute();
//            
//         ResultSet rs = ps.getGeneratedKeys();
//         if (rs.next()) 
//         {
//         System.out.println(rs.getInt(1));
//         }
//         } catch (SQLException ex) {
//         Logger.getLogger(DbCar.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         */    }
//}
