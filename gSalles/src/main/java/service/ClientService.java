package service;

import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import beans.Client;
public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {
		 String sql = "insert into client values (null, ?, ?,?,?)";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setString(1, o.getNom());
	            ps.setString(2,o.getPrenom());
	            ps.setString(3, o.getLogin());
	            ps.setString(4, o.getMdp());
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("create : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public boolean delete(Client o) {
		String sql = "delete from client where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("delete : erreur sql : " + e.getMessage());

        }
        return false;
	}

	@Override
	public boolean update(Client o) {
		String sql = "update client set nom = ? , prenom = ?, login= ? , mdp = ? where id  = ?";
		 try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setString(1, o.getNom());
	            ps.setString(2,o.getPrenom());
	            ps.setString(3, o.getLogin());
	            ps.setString(4, o.getMdp());
	            ps.setInt(5, o.getId());
	           
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("update : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public Client findById(int id) {
		
		Client m = null;
        String sql = "select * from client where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<Client>();

        String sql = "select * from client";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),rs.getString("mdp")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return clients;
	}
	}

