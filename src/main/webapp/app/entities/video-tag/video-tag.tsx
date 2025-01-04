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

import { getEntities } from './video-tag.reducer';

export const VideoTag = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const videoTagList = useAppSelector(state => state.videoTag.entities);
  const loading = useAppSelector(state => state.videoTag.loading);

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
      <h2 id="video-tag-heading" data-cy="VideoTagHeading">
        <Translate contentKey="affliateMarketplaceApp.videoTag.home.title">Video Tags</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.videoTag.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/video-tag/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.videoTag.home.createLabel">Create new Video Tag</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {videoTagList && videoTagList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.name">Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th className="hand" onClick={sort('isModerated')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.isModerated">Is Moderated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isModerated')} />
                </th>
                <th className="hand" onClick={sort('isDeleted')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.isDeleted">Is Deleted</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isDeleted')} />
                </th>
                <th className="hand" onClick={sort('deletionReason')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.deletionReason">Deletion Reason</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deletionReason')} />
                </th>
                <th className="hand" onClick={sort('mergedWithTagName')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.mergedWithTagName">Merged With Tag Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mergedWithTagName')} />
                </th>
                <th className="hand" onClick={sort('mergedWithTagCode')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.mergedWithTagCode">Merged With Tag Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mergedWithTagCode')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.videoTag.posts">Posts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {videoTagList.map((videoTag, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/video-tag/${videoTag.id}`} color="link" size="sm">
                      {videoTag.id}
                    </Button>
                  </td>
                  <td>{videoTag.name}</td>
                  <td>{videoTag.code}</td>
                  <td>{videoTag.isModerated ? 'true' : 'false'}</td>
                  <td>{videoTag.isDeleted ? 'true' : 'false'}</td>
                  <td>{videoTag.deletionReason ? 'true' : 'false'}</td>
                  <td>{videoTag.mergedWithTagName}</td>
                  <td>{videoTag.mergedWithTagCode}</td>
                  <td>{videoTag.isActive ? 'true' : 'false'}</td>
                  <td>{videoTag.createdBy}</td>
                  <td>
                    {videoTag.createdOn ? <TextFormat type="date" value={videoTag.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{videoTag.updatedBy}</td>
                  <td>
                    {videoTag.updatedOn ? <TextFormat type="date" value={videoTag.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {videoTag.posts
                      ? videoTag.posts.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video-post/${val.id}`}>{val.id}</Link>
                            {j === videoTag.posts.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/video-tag/${videoTag.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/video-tag/${videoTag.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/video-tag/${videoTag.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.videoTag.home.notFound">No Video Tags found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default VideoTag;
