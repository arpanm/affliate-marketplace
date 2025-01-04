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

import { getEntities } from './video-post.reducer';

export const VideoPost = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const videoPostList = useAppSelector(state => state.videoPost.entities);
  const loading = useAppSelector(state => state.videoPost.loading);

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
      <h2 id="video-post-heading" data-cy="VideoPostHeading">
        <Translate contentKey="affliateMarketplaceApp.videoPost.home.title">Video Posts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.videoPost.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/video-post/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.videoPost.home.createLabel">Create new Video Post</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {videoPostList && videoPostList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.title">Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('url')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.url">Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('url')} />
                </th>
                <th className="hand" onClick={sort('urlType')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.urlType">Url Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('urlType')} />
                </th>
                <th className="hand" onClick={sort('isAIGenerated')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.isAIGenerated">Is AI Generated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isAIGenerated')} />
                </th>
                <th className="hand" onClick={sort('isPremium')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.isPremium">Is Premium</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isPremium')} />
                </th>
                <th className="hand" onClick={sort('isBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.isBlocked">Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isBlocked')} />
                </th>
                <th className="hand" onClick={sort('isModerated')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.isModerated">Is Moderated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isModerated')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.tags">Tags</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.videoPost.creator">Creator</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {videoPostList.map((videoPost, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/video-post/${videoPost.id}`} color="link" size="sm">
                      {videoPost.id}
                    </Button>
                  </td>
                  <td>{videoPost.title}</td>
                  <td>{videoPost.description}</td>
                  <td>{videoPost.url}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.UrlType.${videoPost.urlType}`} />
                  </td>
                  <td>{videoPost.isAIGenerated ? 'true' : 'false'}</td>
                  <td>{videoPost.isPremium ? 'true' : 'false'}</td>
                  <td>{videoPost.isBlocked ? 'true' : 'false'}</td>
                  <td>{videoPost.isModerated ? 'true' : 'false'}</td>
                  <td>{videoPost.isActive ? 'true' : 'false'}</td>
                  <td>{videoPost.createdBy}</td>
                  <td>
                    {videoPost.createdOn ? <TextFormat type="date" value={videoPost.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{videoPost.updatedBy}</td>
                  <td>
                    {videoPost.updatedOn ? <TextFormat type="date" value={videoPost.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {videoPost.tags
                      ? videoPost.tags.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video-tag/${val.id}`}>{val.id}</Link>
                            {j === videoPost.tags.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>{videoPost.creator ? <Link to={`/video-user/${videoPost.creator.id}`}>{videoPost.creator.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/video-post/${videoPost.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/video-post/${videoPost.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/video-post/${videoPost.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.videoPost.home.notFound">No Video Posts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default VideoPost;
