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

import { getEntities } from './affinity.reducer';

export const Affinity = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const affinityList = useAppSelector(state => state.affinity.entities);
  const loading = useAppSelector(state => state.affinity.loading);

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
      <h2 id="affinity-heading" data-cy="AffinityHeading">
        <Translate contentKey="affliateMarketplaceApp.affinity.home.title">Affinities</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.affinity.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/affinity/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.affinity.home.createLabel">Create new Affinity</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {affinityList && affinityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('segment')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.segment">Segment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('segment')} />
                </th>
                <th className="hand" onClick={sort('score')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.score">Score</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('score')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.affinity.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.affinity.posts">Posts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.affinity.users">Users</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {affinityList.map((affinity, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/affinity/${affinity.id}`} color="link" size="sm">
                      {affinity.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.Segment.${affinity.segment}`} />
                  </td>
                  <td>{affinity.score}</td>
                  <td>{affinity.isActive ? 'true' : 'false'}</td>
                  <td>{affinity.createdBy}</td>
                  <td>
                    {affinity.createdOn ? <TextFormat type="date" value={affinity.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{affinity.updatedBy}</td>
                  <td>
                    {affinity.updatedOn ? <TextFormat type="date" value={affinity.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {affinity.posts
                      ? affinity.posts.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video-post/${val.id}`}>{val.id}</Link>
                            {j === affinity.posts.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {affinity.users
                      ? affinity.users.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video-user/${val.id}`}>{val.id}</Link>
                            {j === affinity.users.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/affinity/${affinity.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/affinity/${affinity.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/affinity/${affinity.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.affinity.home.notFound">No Affinities found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Affinity;
