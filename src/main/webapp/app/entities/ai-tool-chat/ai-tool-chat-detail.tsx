import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ai-tool-chat.reducer';

export const AiToolChatDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const aiToolChatEntity = useAppSelector(state => state.aiToolChat.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="aiToolChatDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.aiToolChat.detail.title">AiToolChat</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.id}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.message">Message</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.message}</dd>
          <dt>
            <span id="videoUrl">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.videoUrl">Video Url</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.videoUrl}</dd>
          <dt>
            <span id="paymentUrl">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.paymentUrl">Payment Url</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.paymentUrl}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.type">Type</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.type}</dd>
          <dt>
            <span id="isFinalVidel">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.isFinalVidel">Is Final Videl</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.isFinalVidel ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDownloaded">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.isDownloaded">Is Downloaded</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.isDownloaded ? 'true' : 'false'}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolChatEntity.createdOn ? (
              <TextFormat value={aiToolChatEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{aiToolChatEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolChat.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolChatEntity.updatedOn ? (
              <TextFormat value={aiToolChatEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.aiToolChat.session">Session</Translate>
          </dt>
          <dd>{aiToolChatEntity.session ? aiToolChatEntity.session.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/ai-tool-chat" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ai-tool-chat/${aiToolChatEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AiToolChatDetail;
