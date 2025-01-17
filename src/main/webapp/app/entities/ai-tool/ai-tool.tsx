import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './ai-tool.reducer';

export const AiTool = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const aiToolList = useAppSelector(state => state.aiTool.entities);
  const loading = useAppSelector(state => state.aiTool.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="ai-tool-heading" data-cy="AiToolHeading">
        <Translate contentKey="affliateMarketplaceApp.aiTool.home.title">Ai Tools</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.aiTool.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ai-tool/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.aiTool.home.createLabel">Create new Ai Tool</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {aiToolList && aiToolList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.name">Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('vendor')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.vendor">Vendor</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vendor')} />
                </th>
                <th className="hand" onClick={sort('linkUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.linkUrl">Link Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('linkUrl')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiTool.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {aiToolList.map((aiTool, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ai-tool/${aiTool.id}`} color="link" size="sm">
                      {aiTool.id}
                    </Button>
                  </td>
                  <td>{aiTool.name}</td>
                  <td>{aiTool.description}</td>
                  <td>{aiTool.vendor}</td>
                  <td>{aiTool.linkUrl}</td>
                  <td>{aiTool.isActive ? 'true' : 'false'}</td>
                  <td>{aiTool.createdBy}</td>
                  <td>{aiTool.createdOn ? <TextFormat type="date" value={aiTool.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{aiTool.updatedBy}</td>
                  <td>{aiTool.updatedOn ? <TextFormat type="date" value={aiTool.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ai-tool/${aiTool.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ai-tool/${aiTool.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/ai-tool/${aiTool.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="affliateMarketplaceApp.aiTool.home.notFound">No Ai Tools found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AiTool;
