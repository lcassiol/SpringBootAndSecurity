package com.lcassiol.utils;

public class JdbcUtils {

    public static final String USUARIO_POR_LOGIN = "SELECT email, password, active FROM user WHERE email=?";

    public static final String ROLES = "SELECT u.email, r.role FROM user u INNER JOIN user_role ur on(u.user_id=ur.user_id) INNER JOIN role r on(ur.role_id=r.role_id) WHERE u.email=?";

    public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome as nome_permissao FROM testdbc.usuario_permissao up"
            + " JOIN testdbc.usuario u ON u.id = up.usuario_id"
            + " JOIN testdbc.permissao p ON p.id = up.permissao_id"
            + " WHERE u.login = ?";

    public static final String PERMISSOES_POR_GRUPO = "SELECT g.id, g.nome, p.nome  as nome_permissao FROM testdbc.grupo_permissao gp"
            + " JOIN testdbc.grupo g ON g.id = gp.grupo_id"
            + " JOIN testdbc.permissao p ON p.id = gp.permissao_id"
            + " JOIN testdbc.usuario_grupo ug ON ug.grupo_id = g.id"
            + " JOIN testdbc.usuario u ON u.id = ug.usuario_id"
            + " WHERE u.login = ?";

}