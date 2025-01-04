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

import { getEntities } from './video-user.reducer';

export const VideoUser = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const videoUserList = useAppSelector(state => state.videoUser.entities);
  const loading = useAppSelector(state => state.videoUser.loading);

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
      <h2 id="video-user-heading" data-cy="VideoUserHeading">
        <Translate contentKey="affliateMarketplaceApp.videoUser.home.title">Video Users</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.videoUser.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/video-user/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.videoUser.home.createLabel">Create new Video User</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {videoUserList && videoUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('userId')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.userId">User Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('userId')} />
                </th>
                <th className="hand" onClick={sort('userName')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.userName">User Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('userName')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.name">Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th className="hand" onClick={sort('phone')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.phone">Phone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phone')} />
                </th>
                <th className="hand" onClick={sort('email')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.email">Email</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('email')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('imageUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.imageUrl">Image Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('imageUrl')} />
                </th>
                <th className="hand" onClick={sort('userType')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.userType">User Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('userType')} />
                </th>
                <th className="hand" onClick={sort('isBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.isBlocked">Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isBlocked')} />
                </th>
                <th className="hand" onClick={sort('blockedTill')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.blockedTill">Blocked Till</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('blockedTill')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoUser.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {videoUserList.map((videoUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/video-user/${videoUser.id}`} color="link" size="sm">
                      {videoUser.id}
                    </Button>
                  </td>
                  <td>{videoUser.userId}</td>
                  <td>{videoUser.userName}</td>
                  <td>{videoUser.name}</td>
                  <td>{videoUser.phone}</td>
                  <td>{videoUser.email}</td>
                  <td>{videoUser.description}</td>
                  <td>{videoUser.imageUrl}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.VideoUserType.${videoUser.userType}`} />
                  </td>
                  <td>{videoUser.isBlocked ? 'true' : 'false'}</td>
                  <td>
                    {videoUser.blockedTill ? <TextFormat type="date" value={videoUser.blockedTill} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{videoUser.isActive ? 'true' : 'false'}</td>
                  <td>{videoUser.createdBy}</td>
                  <td>
                    {videoUser.createdOn ? <TextFormat type="date" value={videoUser.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{videoUser.updatedBy}</td>
                  <td>
                    {videoUser.updatedOn ? <TextFormat type="date" value={videoUser.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/video-user/${videoUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/video-user/${videoUser.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/video-user/${videoUser.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.videoUser.home.notFound">No Video Users found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default VideoUser;
