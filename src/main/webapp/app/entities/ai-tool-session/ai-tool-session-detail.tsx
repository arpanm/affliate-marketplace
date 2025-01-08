import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ai-tool-session.reducer';

export const AiToolSessionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const aiToolSessionEntity = useAppSelector(state => state.aiToolSession.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="aiToolSessionDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.aiToolSession.detail.title">AiToolSession</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.id}</dd>
          <dt>
            <span id="isPaymentLinkGenerated">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.isPaymentLinkGenerated">Is Payment Link Generated</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.isPaymentLinkGenerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isPaid">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.isPaid">Is Paid</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.isPaid ? 'true' : 'false'}</dd>
          <dt>
            <span id="isVideoGenerated">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.isVideoGenerated">Is Video Generated</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.isVideoGenerated ? 'true' : 'false'}</dd>
          <dt>
            <span id="isVideoDownloaded">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.isVideoDownloaded">Is Video Downloaded</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.isVideoDownloaded ? 'true' : 'false'}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolSessionEntity.createdOn ? (
              <TextFormat value={aiToolSessionEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{aiToolSessionEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolSession.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolSessionEntity.updatedOn ? (
              <TextFormat value={aiToolSessionEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.aiToolSession.user">User</Translate>
          </dt>
          <dd>{aiToolSessionEntity.user ? aiToolSessionEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.aiToolSession.tool">Tool</Translate>
          </dt>
          <dd>{aiToolSessionEntity.tool ? aiToolSessionEntity.tool.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/ai-tool-session" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ai-tool-session/${aiToolSessionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AiToolSessionDetail;
