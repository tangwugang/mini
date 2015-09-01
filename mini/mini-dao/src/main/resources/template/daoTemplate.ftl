<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bussiPackage}.${entityPackage}.mapper.${entityName}Mapper">
	<!-- Result Map -->
	<resultMap id="BaseResultMap"
		type="${bussiPackage}.${entityPackage}.entity.${entityName}">
		<#list columnDatas as item>
		<result column="${item.columnName}" property="${item.columnName}" />
		</#list>
	</resultMap>
	
	<cache readOnly="true"/>

	<!-- ${tableName} table all fields -->
	<sql id="Base_Column_List">
		${SQL.columnFields}
	</sql>

	<!-- 查询条件 -->
	<sql id="Example_Where_Clause">
		${SQL.whereSql}
	</sql>
	
	<!-- 批量更新条件 -->
	<sql id="UpdateBatch_Clause">
		${SQL.updateBatchSql}
	</sql>

	<!-- 插入记录 -->
	<insert id="insert" parameterType="Object">
		${SQL.insert}
	</insert>
	
	<!-- 获取对象全部结果集 -->
	<select id="select" resultMap="BaseResultMap">
		${SQL.selectSql} 
	</select>
	
	<!-- 获取对象 -->
	<select id="selectPk" resultMap="BaseResultMap" parameterType="Object">
		${SQL.selectById}
	</select>
	
	<!-- 按条件查询记录   -->
	<select id="selectParam" resultMap="BaseResultMap">
		${SQL.selectParam}
	</select>

	<!-- 修改记录，只修改只不为空的字段 -->
	<update id="update" parameterType="Object">
		${SQL.updateSelective}
	</update>
	
	<!-- 修改记录，只修改只不为空的字段 -->
	<update id="updateParam">
		${SQL.updateSelective}
	</update>
	
	<!--整表总记录数 -->
	<select id="count" resultType="java.lang.Integer">
		${SQL.countSql}
	</select>
	
	<!-- 查询符合条件的总记录数 -->
	<select id="countParam" parameterType="Object" resultType="java.lang.Integer">
		${SQL.countParamSql}
	</select>

	<!-- 删除记录 -->
	<delete id="delete" parameterType="Object">
		${SQL.delete}
	</delete>
	
	<!-- 删除记录 -->
	<delete id="deleteParam">
		${SQL.deleteParamSql}
	</delete>
	
	<!-- 批量删除记录 -->
	<delete id="deleteBatch" parameterType="java.util.List">
		${SQL.deleteBatchSql}
	</delete>
	
	<!-- 批量添加 -->
	<insert id="insertBatch" parameterType="java.util.List">
		${SQL.insertBatch}
	</insert>
	
	<!-- 批量更新 -->
	<update id="updateBatch" parameterType="java.util.List">
		${SQL.updateBatch}
	</update>
</mapper>   
