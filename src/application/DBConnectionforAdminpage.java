package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;





public class DBConnectionforAdminpage {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3306;
	private static final String DB_NAME = "cabinet";
	
	public static Connection connect() throws SQLException {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST +":"+ PORT +"/"+DB_NAME, USERNAME, PASSWORD);
			return conn;
			
		} catch (SQLException e) {
			Logger.getLogger(DBConnectionforAdminpage.class.getName()).log(Level.SEVERE,null,e);
			return null;
		}
		
		
		
	}
	public static int update(int id,String password) {
		int st = 0;
		try {
			String sql = "UPDATE users SET password=? WHERE id_users=?";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1, password);
			stat.setInt(2, id);
			st = stat.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public static int add_for_rendezvous(Model_rendezvous rdv) {
		int st=0;
		
		
		try {
			
			String sql = "INSERT INTO rendezvous(prenom,nom,telephone,date) VALUES(?,?,?,?)";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat ;
			stat = con.prepareStatement(sql);
			stat.setString(1, rdv.getPrenom());
			stat.setString(2, rdv.getNom());
			stat.setInt(3, rdv.getTelephone());
			stat.setString(4, rdv.getDate());
			st = stat.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return st;
	}
	
	public static int update_for_rendezvous(Model_rendezvous rdv) {
		
		int st=0;
		
		try {
			String sql = "UPDATE rendezvous SET prenom=?, nom=?, telephone=?, date=? WHERE id_rendezvous=?";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1,rdv.getPrenom());
			stat.setString(2, rdv.getNom());
			stat.setInt(3, rdv.getTelephone());
			stat.setString(4, rdv.getDate());
			stat.setInt(5, rdv.getId());
			st = stat.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return st;
	}
	
	public static int delete_for_rendezvous(Model_rendezvous rdv) {
		int st=0;
		try {
			String sql = "DELETE FROM rendezvous WHERE id_rendezvous=?";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setInt(1, rdv.getId());
			st = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		return st;
	}
	
	/* this for Fiche page ---------------------------------------------------------------------*/
	/* this for Fiche page ---------------------------------------------------------------------*/
	/* this for Fiche page ---------------------------------------------------------------------*/
	/* this for Fiche page ---------------------------------------------------------------------*/
	public static int add_for_fiche(Model_Fiche_Page rdv) {
		int st=0;
		
		
		try {
			
			String sql = "INSERT INTO fiche(prenom,nom,date_naiss,cin,profession,mutuelle,date_creation,comment) VALUES(?,?,?,?,?,?,?,?)";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat ;
			stat = con.prepareStatement(sql);
			stat.setString(1, rdv.getPrenom());
			stat.setString(2, rdv.getNom());
			stat.setString(3, rdv.getDate_n());
			stat.setString(4, rdv.getCin());
			stat.setString(5, rdv.getProfession());
			stat.setString(6, rdv.getMutuelle());
			stat.setString(7, rdv.getDate_cre());
			stat.setString(8, rdv.getComm());
			
			st = stat.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return st;
	}
	
public static int update_for_fiche(Model_Fiche_Page rdv) {
		
		int st=0;
		
		try {
			String sql = "UPDATE fiche SET prenom=?, nom=?, date_naiss=?, cin=?, profession=?, mutuelle=?, date_creation=?, comment=? WHERE id=?";
			Connection con = DBConnectionforAdminpage.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1, rdv.getPrenom());
			stat.setString(2, rdv.getNom());
			stat.setString(3, rdv.getDate_n());
			stat.setString(4, rdv.getCin());
			stat.setString(5, rdv.getProfession());
			stat.setString(6, rdv.getMutuelle());
			stat.setString(7, rdv.getDate_cre());
			stat.setString(8, rdv.getComm());
			stat.setInt(9, rdv.getId());
			st = stat.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return st;
	}


		public static int delete_for_fiche(Model_Fiche_Page rdv) {
				int st=0;
					try {
							String sql = "DELETE FROM fiche WHERE id=?";
							Connection con = DBConnectionforAdminpage.connect();
							PreparedStatement stat;
							stat = con.prepareStatement(sql);
							stat.setInt(1, rdv.getId());
							st = stat.executeUpdate();
							con.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
	
					return st;
		}
		
		/* this for Facture page ---------------------------------------------------------------------*/
		/* this for Facture page ---------------------------------------------------------------------*/
		/* this for Facture page ---------------------------------------------------------------------*/
		/* this for Facture page ---------------------------------------------------------------------*/
		
		public static int add_for_facture(Model_Facture_Page rdv) {
			int st=0;
			
			
			try {
				
				String sql = "INSERT INTO facure(n_client,date,cotact_client,telephone,prix_total) VALUES(?,?,?,?,?)";
				Connection con = DBConnectionforAdminpage.connect();
				PreparedStatement stat ;
				stat = con.prepareStatement(sql);
				stat.setString(1, rdv.getN_client());
				stat.setString(2, rdv.getDate());
				stat.setString(3, rdv.getContact_client());
				stat.setInt(4, rdv.getTelephone());
				stat.setInt(5, rdv.getPrix_total());
				
				
				st = stat.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			return st;
		}
		
		
		public static int update_for_Facture_Page(Model_Facture_Page rdv) {
			
			int st=0;
			
			try {
				String sql = "UPDATE facure SET n_client=?, date=?, cotact_client=?, telephone=?, prix_total=? WHERE id=?";
				Connection con = DBConnectionforAdminpage.connect();
				PreparedStatement stat;
				stat = con.prepareStatement(sql);
				stat.setString(1, rdv.getN_client());
				stat.setString(2, rdv.getDate());
				stat.setString(3, rdv.getContact_client());
				stat.setInt(4, rdv.getTelephone());
				stat.setInt(5, rdv.getPrix_total());
				stat.setInt(6, rdv.getId());
				st = stat.executeUpdate();
				con.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			return st;
		}
		
		
		public static int delete_for_Facture_Page(Model_Facture_Page rdv) {
			int st=0;
				try {
						String sql = "DELETE FROM facure WHERE id=?";
						Connection con = DBConnectionforAdminpage.connect();
						PreparedStatement stat;
						stat = con.prepareStatement(sql);
						stat.setInt(1, rdv.getId());
						st = stat.executeUpdate();
						con.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				return st;
		}
		
		
		/*============================= Ordonnance ==========================================================*/
		/*============================= Ordonnance ==========================================================*/
		/*============================= Ordonnance ==========================================================*/
		/*============================= Ordonnance ==========================================================*/
	
		
		public static int add_for_Orfonnance_Page(Model_Ordonnance_Page rdv) {
			int st=0;
			
			
			try {
				
				String sql = "INSERT INTO ordonnance(nom,adresse,qualite,denomination,forme,posoligie,mode_emp) VALUES(?,?,?,?,?,?,?)";
				Connection con = DBConnectionforAdminpage.connect();
				PreparedStatement stat ;
				stat = con.prepareStatement(sql);
				stat.setString(1, rdv.getNom());
				stat.setString(2, rdv.getAddress());
				stat.setString(3, rdv.getQualite());
				stat.setString(4, rdv.getDenomination());
				stat.setString(5, rdv.getForme());
				stat.setString(6, rdv.getPosologie());
				stat.setString(7, rdv.getMode_emploi());
				
				
				st = stat.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			return st;
		}
		
			public static int update_for_Ordonnance_Page(Model_Ordonnance_Page rdv) {
			
				int st=0;
			
				try {
					String sql = "UPDATE ordonnance SET nom=?, adresse=?, qualite=?, denomination=?, forme=?, posoligie=?, mode_emp=? WHERE id=?";
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setString(1, rdv.getNom());
					stat.setString(2, rdv.getAddress());
					stat.setString(3, rdv.getQualite());
					stat.setString(4, rdv.getDenomination());
					stat.setString(5, rdv.getForme());
					stat.setString(6, rdv.getPosologie());
					stat.setString(7, rdv.getMode_emploi());
					stat.setInt(8, rdv.getId());
					st = stat.executeUpdate();
					con.close();
				
				} catch (SQLException e) {
				
					e.printStackTrace();
				}

				return st;
			}	
			
			public static int delete_for_Ordonnance_Page(Model_Ordonnance_Page rdv) {
				int st=0;
					try {
							String sql = "DELETE FROM ordonnance WHERE id=?";
							Connection con = DBConnectionforAdminpage.connect();
							PreparedStatement stat;
							stat = con.prepareStatement(sql);
							stat.setInt(1, rdv.getId());
							st = stat.executeUpdate();
							con.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}

					return st;
			}
			
			
			/*============================= Certificat ==========================================================*/
			/*============================= Certificat ==========================================================*/
			/*============================= Certificat ==========================================================*/
			/*============================= Certificat ==========================================================*/
			
			public static int add_for_Certificat_Page(Model_Certificat_Page rdv) {
				int st=0;
				
				
				try {
					
					String sql = "INSERT INTO certificat(nom,date_naissance,etabli_a,date) VALUES(?,?,?,?)";
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat ;
					stat = con.prepareStatement(sql);
					stat.setString(1, rdv.getNom());
					stat.setString(2, rdv.getDate_naiss());
					stat.setString(3, rdv.getEtabli_a());
					stat.setString(4, rdv.getDate());
					
					
					
					st = stat.executeUpdate();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
				
				return st;
			}
			
			
			public static int update_for_Certificat_Page(Model_Certificat_Page rdv) {
				
				int st=0;
			
				try {
					String sql = "UPDATE certificat SET nom=?, date_naissance=?, etabli_a=?, date=? WHERE id=?";
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setString(1, rdv.getNom());
					stat.setString(2, rdv.getDate_naiss());
					stat.setString(3, rdv.getEtabli_a());
					stat.setString(4, rdv.getDate());
					stat.setInt(5, rdv.getId());
					st = stat.executeUpdate();
					con.close();
				
				} catch (SQLException e) {
				
					e.printStackTrace();
				}

				return st;
			}	
			
			public static int delete_for_Certificat_Page(Model_Certificat_Page rdv) {
				int st=0;
					try {
							String sql = "DELETE FROM certificat WHERE id=?";
							Connection con = DBConnectionforAdminpage.connect();
							PreparedStatement stat;
							stat = con.prepareStatement(sql);
							stat.setInt(1, rdv.getId());
							st = stat.executeUpdate();
							con.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					return st;
			}
			
			/*============================= Diagnostics ==========================================================*/
			/*============================= Diagnostics ==========================================================*/
			/*============================= Diagnostics ==========================================================*/
			/*============================= Diagnostics ==========================================================*/
			
			public static int add_for_Diagnostics_Page(Model_Diagnostics_Page rdv) {
				int st=0;
				
				
				try {
					
					String sql = "INSERT INTO diagnostics(cin,choix_1,choix_2,choix_3,choix_4,choix_5,choix_6,choix_7,choix_8,choix_9,choix_10,choix_11,choix_12,choix_13,choix_14,choix_15,choix_16,choix_17,choix_18,choix_19,choix_20,choix_21,choix_22) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat ;
					stat = con.prepareStatement(sql);
					stat.setString(1, rdv.getCin());
					stat.setBoolean(2 , rdv.getChoix_1());
					stat.setBoolean(3 , rdv.getChoix_2());
					stat.setBoolean(4 , rdv.getChoix_3());
					stat.setBoolean(5 , rdv.getChoix_4());
					stat.setBoolean(6 , rdv.getChoix_5());
					stat.setBoolean(7 , rdv.getChoix_6());
					stat.setBoolean(8 , rdv.getChoix_7());
					stat.setBoolean(9 , rdv.getChoix_8());
					stat.setBoolean(10 , rdv.getChoix_9());
					stat.setBoolean(11 , rdv.getChoix_10());
					stat.setBoolean(12 , rdv.getChoix_11());
					stat.setBoolean(13 , rdv.getChoix_12());
					stat.setBoolean(14 , rdv.getChoix_13());
					stat.setBoolean(15 , rdv.getChoix_14());
					stat.setBoolean(16 , rdv.getChoix_15());
					stat.setBoolean(17 , rdv.getChoix_16());
					stat.setBoolean(18 , rdv.getChoix_17());
					stat.setBoolean(19 , rdv.getChoix_18());
					stat.setBoolean(20 , rdv.getChoix_19());
					stat.setBoolean(21 , rdv.getChoix_20());
					stat.setBoolean(22 , rdv.getChoix_21());
					stat.setBoolean(23 , rdv.getChoix_22());
					

					
					
					
					st = stat.executeUpdate();
					con.close();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
				
				return st;
			}
			
			public static int Checking_for_Diagnostics_Page(String new_cin) {
				int st=0;
				
				
				try {
					String sql = "SELECT cin FROM diagnostics WHERE cin=?";
					
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat ;
					stat = con.prepareStatement(sql);
					stat.setString(1 , new_cin);
					

					
					
					
					st = stat.executeUpdate();
					
					con.close();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
				
				return st;
			}
			
			public static int update_for_Diagnostics_Page(Model_Diagnostics_Page rdv) {
				
				int st=0;
			
				try {
					String sql = "UPDATE diagnostics SET choix_1=?, choix_2=?, choix_3=?, choix_4=?, choix_5=?, choix_6=?, choix_7=?, choix_8=?, choix_9=?, choix_10=?, choix_11=?, choix_12=?, choix_13=?, choix_14=?, choix_15=?, choix_16=?, choix_17=?, choix_18=?, choix_19=?, choix_20=?, choix_21=?, choix_22=? WHERE cin=?";
					Connection con = DBConnectionforAdminpage.connect();
					PreparedStatement stat;
					stat = con.prepareStatement(sql);
					stat.setBoolean(1 , rdv.getChoix_1());
					stat.setBoolean(2 , rdv.getChoix_2());
					stat.setBoolean(3 , rdv.getChoix_3());
					stat.setBoolean(4 , rdv.getChoix_4());
					stat.setBoolean(5 , rdv.getChoix_5());
					stat.setBoolean(6 , rdv.getChoix_6());
					stat.setBoolean(7 , rdv.getChoix_7());
					stat.setBoolean(8 , rdv.getChoix_8());
					stat.setBoolean(9 , rdv.getChoix_9());
					stat.setBoolean(10 , rdv.getChoix_10());
					stat.setBoolean(11 , rdv.getChoix_11());
					stat.setBoolean(12 , rdv.getChoix_12());
					stat.setBoolean(13 , rdv.getChoix_13());
					stat.setBoolean(14 , rdv.getChoix_14());
					stat.setBoolean(15 , rdv.getChoix_15());
					stat.setBoolean(16 , rdv.getChoix_16());
					stat.setBoolean(17 , rdv.getChoix_17());
					stat.setBoolean(18 , rdv.getChoix_18());
					stat.setBoolean(19 , rdv.getChoix_19());
					stat.setBoolean(20 , rdv.getChoix_20());
					stat.setBoolean(21 , rdv.getChoix_21());
					stat.setBoolean(22 , rdv.getChoix_22());
					stat.setString(23, rdv.getCin());
					st = stat.executeUpdate();
					con.close();
				
				} catch (SQLException e) {
				
					e.printStackTrace();
				}

				return st;
			}
			
			public static int delete_for_Diagnostics_Page(Model_Diagnostics_Page rdv) {
				int st=0;
					try {
							String sql = "DELETE FROM diagnostics WHERE cin=?";
							Connection con = DBConnectionforAdminpage.connect();
							PreparedStatement stat;
							stat = con.prepareStatement(sql);
							stat.setString(1, rdv.getCin());
							st = stat.executeUpdate();
							con.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					return st;
			}

}
