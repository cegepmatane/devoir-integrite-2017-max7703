package ca.qc.cgmatane.informatique.devoirintegrite.accesseur;

import java.sql.*;

public class BaseDeDonnee {
    private static final String DRIVER = "org.postgresql.Driver";

    public static Connection connexion = null;

	public static String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/test";
	public static String BASEDEDONNEES_USAGER = "postgres";
	public static String BASEDEDONNEES_MOTDEPASSE = "test";


    public static void ConnexionBDD() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("JBDC Driver non trouvé?");
            e.printStackTrace();
            throw e;
        }

        try {
            connexion = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
        } catch (SQLException e) {
            System.out.println("Connexion echouée!" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void DeconnexionBDD() throws SQLException {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
}

