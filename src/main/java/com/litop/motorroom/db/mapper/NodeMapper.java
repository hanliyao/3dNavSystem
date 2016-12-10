package com.litop.motorroom.db.mapper;

import com.litop.motorroom.db.bean.Node;
//import com.litop.motorroom.page.PageInfo;
import com.litop.motorroom.page.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    /**
     * 获取系统根菜单
     * @param parentId 父节点ID
     * @return
     * @throws Exception
     */
    List<Node> selectNodesByPid(Integer parentId);

    List<Node> getAll();

    List<Node> select();

    List<Node> getNodesTreeWithoutId(Integer id);

	List<Node> getListByAuthType(Integer authType);


   //List<Node> selectNodeListPage(@Param("offset") int offset, @Param("pagesize") int pagesize);

    List<Node> selectNodeListPage(Map paramMap);


    int getCounts(Map paramMap);

    String getIdbyAction(String action);
}