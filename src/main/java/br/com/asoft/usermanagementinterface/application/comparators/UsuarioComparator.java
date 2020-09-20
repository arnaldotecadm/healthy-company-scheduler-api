package br.com.asoft.usermanagementinterface.application.comparators;

import java.util.Comparator;

import br.com.asoft.usermanagementinterface.model.Usuario;

public class UsuarioComparator implements Comparator<Usuario>
{
    public int compare(Usuario user1, Usuario user2)
    {
        return user1.getId().compareTo(user2.getId());
    }
}
