import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './competition-payment-from-sponsor.reducer';

export const CompetitionPaymentFromSponsorDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const competitionPaymentFromSponsorEntity = useAppSelector(state => state.competitionPaymentFromSponsor.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="competitionPaymentFromSponsorDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.detail.title">
            CompetitionPaymentFromSponsor
          </Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.amount}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.transactionId}</dd>
          <dt>
            <span id="transactionScreenshotUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionScreenshotUrl">
                Transaction Screenshot Url
              </Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.transactionScreenshotUrl}</dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentFromSponsorEntity.transactionDate ? (
              <TextFormat value={competitionPaymentFromSponsorEntity.transactionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="transactionStatus">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.transactionStatus">Transaction Status</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.transactionStatus}</dd>
          <dt>
            <span id="paidBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.paidBy">Paid By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.paidBy}</dd>
          <dt>
            <span id="paymentReceiptUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.paymentReceiptUrl">Payment Receipt Url</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.paymentReceiptUrl}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentFromSponsorEntity.createdOn ? (
              <TextFormat value={competitionPaymentFromSponsorEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentFromSponsorEntity.updatedOn ? (
              <TextFormat value={competitionPaymentFromSponsorEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.competition">Competition</Translate>
          </dt>
          <dd>{competitionPaymentFromSponsorEntity.competition ? competitionPaymentFromSponsorEntity.competition.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/competition-payment-from-sponsor" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/competition-payment-from-sponsor/${competitionPaymentFromSponsorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CompetitionPaymentFromSponsorDetail;
