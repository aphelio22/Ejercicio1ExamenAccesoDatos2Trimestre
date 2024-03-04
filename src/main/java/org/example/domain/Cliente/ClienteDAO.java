package org.example.domain.Cliente;

import org.example.domain.ObjectUtil;
import org.example.enums.Estado;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {

    public Cliente get(Integer id) {
        Cliente salida = null;
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("select c from Cliente c where c.id= :id", Cliente.class);
            query.setParameter("id", id);
            salida = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return salida;
    }

    public Cliente save(Cliente data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return data;
    }

    public List<Cliente> listarMejoresClientes(Long cantidad) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        List<Cliente> mejoresClientes;
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("select c from Cliente c WHERE c.estado = :estadoActivo and c.total > :cantidad", Cliente.class);
            query.setParameter("estadoActivo", Estado.activo);
            query.setParameter("cantidad", cantidad);

            mejoresClientes = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return mejoresClientes;
    }

    public Long calcularTotalVentas() {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        Long totalVentas;
        try {
            TypedQuery<Long> query = entityManager.createQuery("select SUM(c.total) from Cliente c", Long.class);
            totalVentas = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return totalVentas;
    }

    public Double calcularPromedioVentas() {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        Double promedioVentas;
        try {
            TypedQuery<Double> query = entityManager.createQuery("select AVG(c.total) from Cliente c", Double.class);
            promedioVentas = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return promedioVentas;
    }

    public Long contarClientesInactivosConVentasMayorQueCero() {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        Long cantidadClientesInactivosConVentas;

        try {
            TypedQuery<Long> query = entityManager.createQuery("select COUNT(c) from Cliente c where c.estado = :estadoInactivo and c.total > 0", Long.class);
            query.setParameter("estadoInactivo", Estado.inactivo);
            cantidadClientesInactivosConVentas = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return cantidadClientesInactivosConVentas;
    }
}
