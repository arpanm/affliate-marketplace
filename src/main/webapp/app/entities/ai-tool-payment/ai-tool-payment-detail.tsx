import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ai-tool-payment.reducer';

export const AiToolPaymentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const aiToolPaymentEntity = useAppSelector(state => state.aiToolPayment.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="aiToolPaymentDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.aiToolPayment.detail.title">AiToolPayment</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.amount}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.status">Status</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.status}</dd>
          <dt>
            <span id="paymentLinkUrl">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.paymentLinkUrl">Payment Link Url</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.paymentLinkUrl}</dd>
          <dt>
            <span id="pgType">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgType">Pg Type</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgType}</dd>
          <dt>
            <span id="pgId">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgId">Pg Id</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgId}</dd>
          <dt>
            <span id="pgStatus">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgStatus">Pg Status</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgStatus}</dd>
          <dt>
            <span id="pgRequestJson">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgRequestJson">Pg Request Json</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgRequestJson}</dd>
          <dt>
            <span id="pgResponseJson">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgResponseJson">Pg Response Json</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgResponseJson}</dd>
          <dt>
            <span id="pgRequestTimeStamp">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgRequestTimeStamp">Pg Request Time Stamp</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgRequestTimeStamp}</dd>
          <dt>
            <span id="pgResponseTimeStamp">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.pgResponseTimeStamp">Pg Response Time Stamp</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.pgResponseTimeStamp}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolPaymentEntity.createdOn ? (
              <TextFormat value={aiToolPaymentEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{aiToolPaymentEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.aiToolPayment.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {aiToolPaymentEntity.updatedOn ? (
              <TextFormat value={aiToolPaymentEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.aiToolPayment.session">Session</Translate>
          </dt>
          <dd>{aiToolPaymentEntity.session ? aiToolPaymentEntity.session.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/ai-tool-payment" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ai-tool-payment/${aiToolPaymentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AiToolPaymentDetail;
