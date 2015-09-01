<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bussPackage}.dao.${entityPackage}.${entityName}Dao">
    <!-- Result Map -->
    <resultMap id="BaseResultMap" type="${bussPackage}.entity.${entityPackage}.${entityName}">
    <#list columnData as item>
        <result column="${item.columnName}" property="${item.columnNameByHump}"/>
    </#list>
    </resultMap>

    <sql id="BaseColumnList">
    ${baseColumnList}
    </sql>

    <!-- 查询条件 -->
    <sql id="BaseWhereClause">
    ${whereClause}
    </sql>

    <!-- 批量更新条件 -->
    <sql id="BaseUpdateSet">
    ${baseUpdateSet}
    </sql>

    <!-- 插入记录 -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
    ${insertSql}
    </insert>

    <!-- 获取对象全部结果集 -->
    <select id="select" resultMap="BaseResultMap">
    ${selectSql}
    </select>

    <!-- 查询总数 -->
    <select id = "selectCount" resultType = "java.lang.Integer">
        select count(id)
        from ${tableName}
        <include refid = "BaseWhereClause" />
    </select>


    <!-- 根据IDS批量查询 -->
    <select id = "selectByIds" resultMap = "BaseResultMap">
        ${selectByIdsSql}
    </select>

    <!-- 根据IDS批量删除 -->
    <update id = "deleteByIds">
        ${deleteByIdsSql}
    </update>

    <!-- 根据ID删除 -->
    <update id = "deleteById">
        ${deleteByIdSql}
    </update>

    <!-- 删除 -->
    <delete id = "delete">
        update ${tableName}
        set is_deleted='Y'
        <include refid = "BaseWhereClause" />
    </delete>

    <!-- 通过ID更新 -->
    <update id="updateById">
    ${updateSql}
    </update>

</mapper>
