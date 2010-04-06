package com.geopack.dataloader.nodes;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 14:36:09
 */

/**
 * ��������� ���� field � xml ����� ��
 */
public class XmlField {
//	<field id="orgName" desc="�������� �����������" type="String" key="0" keyTable="" keyField="" keyDescField=""/>
	//id ����
	private String id;
	//������������ ��������
	private String desc;
	//��� ��������
	private String type;
	//������� ��������� ���� 1- ��������
	private String key;
	//�������� ������� - ��������� ��� ��������� ����
	private String keyTable;
	//�������� ���� � ������� - ��������� ��� ��������� ����
	private String keyField;
	//�������� ����-����������� � �������-��������� ��� ��������� ����
	private String keyDescField;

	public XmlField() {
	}

	/**
	 *
	 * @return id ����
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return ������������ ��������
	 */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 *
	 * @return ��� ��������
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @return ������� ��������� ���� 1- ��������
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 *
	 * @return �������� ������� - ��������� ��� ��������� ����
	 */
	public String getKeyTable() {
		return keyTable;
	}

	public void setKeyTable(String keyTable) {
		this.keyTable = keyTable;
	}

	/**
	 *
	 * @return �������� ���� � ������� - ��������� ��� ��������� ����
	 */
	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	/**
	 *
	 * @return �������� ����-����������� � �������-��������� ��� ��������� ����
	 */
	public String getKeyDescField() {
		return keyDescField;
	}

	public void setKeyDescField(String keyDescField) {
		this.keyDescField = keyDescField;
	}
}
