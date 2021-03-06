import React, { useContext } from 'react';
import { Table } from 'choerodon-ui/pro';
import { observer } from 'mobx-react-lite';
import Store from '../../stores';
import './BinTable.less';

const { Column } = Table;

const BinTable = observer(() => {
  const { binTableDataSet } = useContext(Store);

  const renderBelongTo = ({ value, text, name, record, dataSet }) => {
    if (record.get('type') === 'base') {
      return '/';
    } else {
      return '文档所属知识库名';
    }
  };

  const renderType = ({ value, text, name, record, dataSet }) => {
    if (record.get('type') === 'base') {
      return '知识库';
    } else {
      return '文档';
    }
  };

  return (
    <Table className="c7n-kb-binTable" dataSet={binTableDataSet}>
      <Column name="name" />
      <Column name="belongTo" renderer={renderBelongTo} />
      <Column name="type" renderer={renderType} />
      <Column name="deletePerson" />
      <Column name="deleteTime" />
    </Table>
  );
});

export default BinTable;
