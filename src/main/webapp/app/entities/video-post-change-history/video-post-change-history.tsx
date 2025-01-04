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

import { getEntities } from './video-post-change-history.reducer';

export const VideoPostChangeHistory = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const videoPostChangeHistoryList = useAppSelector(state => state.videoPostChangeHistory.entities);
  const loading = useAppSelector(state => state.videoPostChangeHistory.loading);

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
      <h2 id="video-post-change-history-heading" data-cy="VideoPostChangeHistoryHeading">
        <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.home.title">Video Post Change Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/video-post-change-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.home.createLabel">
              Create new Video Post Change History
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {videoPostChangeHistoryList && videoPostChangeHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('changeType')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.changeType">Change Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('changeType')} />
                </th>
                <th className="hand" onClick={sort('oldtitle')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldtitle">Oldtitle</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldtitle')} />
                </th>
                <th className="hand" onClick={sort('oldDescription')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldDescription">Old Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldDescription')} />
                </th>
                <th className="hand" onClick={sort('oldUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUrl">Old Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUrl')} />
                </th>
                <th className="hand" onClick={sort('oldUrlType')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUrlType">Old Url Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUrlType')} />
                </th>
                <th className="hand" onClick={sort('oldIsAIGenerated')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsAIGenerated">Old Is AI Generated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsAIGenerated')} />
                </th>
                <th className="hand" onClick={sort('oldIsPremium')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsPremium">Old Is Premium</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsPremium')} />
                </th>
                <th className="hand" onClick={sort('oldIsBlocked')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsBlocked">Old Is Blocked</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsBlocked')} />
                </th>
                <th className="hand" onClick={sort('oldIsModerated')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsModerated">Old Is Moderated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsModerated')} />
                </th>
                <th className="hand" onClick={sort('oldIsActive')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldIsActive">Old Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldIsActive')} />
                </th>
                <th className="hand" onClick={sort('oldCreatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldCreatedBy">Old Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldCreatedBy')} />
                </th>
                <th className="hand" onClick={sort('oldCreatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldCreatedOn">Old Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldCreatedOn')} />
                </th>
                <th className="hand" onClick={sort('oldUpdatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedBy">Old Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUpdatedBy')} />
                </th>
                <th className="hand" onClick={sort('oldUpdatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedOn">Old Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oldUpdatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.post">Post</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {videoPostChangeHistoryList.map((videoPostChangeHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/video-post-change-history/${videoPostChangeHistory.id}`} color="link" size="sm">
                      {videoPostChangeHistory.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.ChangeType.${videoPostChangeHistory.changeType}`} />
                  </td>
                  <td>{videoPostChangeHistory.oldtitle}</td>
                  <td>{videoPostChangeHistory.oldDescription}</td>
                  <td>{videoPostChangeHistory.oldUrl}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.UrlType.${videoPostChangeHistory.oldUrlType}`} />
                  </td>
                  <td>{videoPostChangeHistory.oldIsAIGenerated ? 'true' : 'false'}</td>
                  <td>{videoPostChangeHistory.oldIsPremium ? 'true' : 'false'}</td>
                  <td>{videoPostChangeHistory.oldIsBlocked ? 'true' : 'false'}</td>
                  <td>{videoPostChangeHistory.oldIsModerated ? 'true' : 'false'}</td>
                  <td>{videoPostChangeHistory.oldIsActive ? 'true' : 'false'}</td>
                  <td>{videoPostChangeHistory.oldCreatedBy}</td>
                  <td>
                    {videoPostChangeHistory.oldCreatedOn ? (
                      <TextFormat type="date" value={videoPostChangeHistory.oldCreatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{videoPostChangeHistory.oldUpdatedBy}</td>
                  <td>
                    {videoPostChangeHistory.oldUpdatedOn ? (
                      <TextFormat type="date" value={videoPostChangeHistory.oldUpdatedOn} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {videoPostChangeHistory.post ? (
                      <Link to={`/video-post/${videoPostChangeHistory.post.id}`}>{videoPostChangeHistory.post.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/video-post-change-history/${videoPostChangeHistory.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/video-post-change-history/${videoPostChangeHistory.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/video-post-change-history/${videoPostChangeHistory.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.home.notFound">
                No Video Post Change Histories found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default VideoPostChangeHistory;
