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

import { getEntities } from './ai-tool-chat.reducer';

export const AiToolChat = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const aiToolChatList = useAppSelector(state => state.aiToolChat.entities);
  const loading = useAppSelector(state => state.aiToolChat.loading);

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
      <h2 id="ai-tool-chat-heading" data-cy="AiToolChatHeading">
        <Translate contentKey="affliateMarketplaceApp.aiToolChat.home.title">Ai Tool Chats</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.aiToolChat.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ai-tool-chat/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.aiToolChat.home.createLabel">Create new Ai Tool Chat</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {aiToolChatList && aiToolChatList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('message')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.message">Message</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('message')} />
                </th>
                <th className="hand" onClick={sort('videoUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.videoUrl">Video Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('videoUrl')} />
                </th>
                <th className="hand" onClick={sort('paymentUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.paymentUrl">Payment Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentUrl')} />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.type">Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('type')} />
                </th>
                <th className="hand" onClick={sort('isFinalVidel')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.isFinalVidel">Is Final Videl</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isFinalVidel')} />
                </th>
                <th className="hand" onClick={sort('isDownloaded')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.isDownloaded">Is Downloaded</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isDownloaded')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th>
                  <Translate contentKey="affliateMarketplaceApp.aiToolChat.session">Session</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {aiToolChatList.map((aiToolChat, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ai-tool-chat/${aiToolChat.id}`} color="link" size="sm">
                      {aiToolChat.id}
                    </Button>
                  </td>
                  <td>{aiToolChat.message}</td>
                  <td>{aiToolChat.videoUrl}</td>
                  <td>{aiToolChat.paymentUrl}</td>
                  <td>
                    <Translate contentKey={`affliateMarketplaceApp.ChatType.${aiToolChat.type}`} />
                  </td>
                  <td>{aiToolChat.isFinalVidel ? 'true' : 'false'}</td>
                  <td>{aiToolChat.isDownloaded ? 'true' : 'false'}</td>
                  <td>{aiToolChat.isActive ? 'true' : 'false'}</td>
                  <td>{aiToolChat.createdBy}</td>
                  <td>
                    {aiToolChat.createdOn ? <TextFormat type="date" value={aiToolChat.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{aiToolChat.updatedBy}</td>
                  <td>
                    {aiToolChat.updatedOn ? <TextFormat type="date" value={aiToolChat.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{aiToolChat.session ? <Link to={`/ai-tool-session/${aiToolChat.session.id}`}>{aiToolChat.session.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ai-tool-chat/${aiToolChat.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ai-tool-chat/${aiToolChat.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/ai-tool-chat/${aiToolChat.id}/delete`)}
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
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.home.notFound">No Ai Tool Chats found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AiToolChat;
