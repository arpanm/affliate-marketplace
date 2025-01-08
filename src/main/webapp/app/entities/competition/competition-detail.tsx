import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './competition.reducer';

export const CompetitionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const competitionEntity = useAppSelector(state => state.competition.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="competitionDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.competition.detail.title">Competition</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="affliateMarketplaceApp.competition.title">Title</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.title}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="affliateMarketplaceApp.competition.description">Description</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.description}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="affliateMarketplaceApp.competition.status">Status</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.status}</dd>
          <dt>
            <span id="paymentStatus">
              <Translate contentKey="affliateMarketplaceApp.competition.paymentStatus">Payment Status</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.paymentStatus}</dd>
          <dt>
            <span id="isBlocked">
              <Translate contentKey="affliateMarketplaceApp.competition.isBlocked">Is Blocked</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="blockReason">
              <Translate contentKey="affliateMarketplaceApp.competition.blockReason">Block Reason</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.blockReason}</dd>
          <dt>
            <span id="blockedBy">
              <Translate contentKey="affliateMarketplaceApp.competition.blockedBy">Blocked By</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.blockedBy}</dd>
          <dt>
            <span id="isPaused">
              <Translate contentKey="affliateMarketplaceApp.competition.isPaused">Is Paused</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.isPaused ? 'true' : 'false'}</dd>
          <dt>
            <span id="pauseReason">
              <Translate contentKey="affliateMarketplaceApp.competition.pauseReason">Pause Reason</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.pauseReason}</dd>
          <dt>
            <span id="pausedBy">
              <Translate contentKey="affliateMarketplaceApp.competition.pausedBy">Paused By</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.pausedBy}</dd>
          <dt>
            <span id="banner1Url">
              <Translate contentKey="affliateMarketplaceApp.competition.banner1Url">Banner 1 Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.banner1Url}</dd>
          <dt>
            <span id="banner2Url">
              <Translate contentKey="affliateMarketplaceApp.competition.banner2Url">Banner 2 Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.banner2Url}</dd>
          <dt>
            <span id="banner3Url">
              <Translate contentKey="affliateMarketplaceApp.competition.banner3Url">Banner 3 Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.banner3Url}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="affliateMarketplaceApp.competition.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {competitionEntity.startDate ? (
              <TextFormat value={competitionEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="affliateMarketplaceApp.competition.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {competitionEntity.endDate ? <TextFormat value={competitionEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="landingUrl">
              <Translate contentKey="affliateMarketplaceApp.competition.landingUrl">Landing Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.landingUrl}</dd>
          <dt>
            <span id="totalPrizeValue">
              <Translate contentKey="affliateMarketplaceApp.competition.totalPrizeValue">Total Prize Value</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.totalPrizeValue}</dd>
          <dt>
            <span id="invoiceToSponsorUrl">
              <Translate contentKey="affliateMarketplaceApp.competition.invoiceToSponsorUrl">Invoice To Sponsor Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.invoiceToSponsorUrl}</dd>
          <dt>
            <span id="tncUrl">
              <Translate contentKey="affliateMarketplaceApp.competition.tncUrl">Tnc Url</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.tncUrl}</dd>
          <dt>
            <span id="tnc">
              <Translate contentKey="affliateMarketplaceApp.competition.tnc">Tnc</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.tnc}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.competition.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.competition.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.competition.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {competitionEntity.createdOn ? (
              <TextFormat value={competitionEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.competition.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{competitionEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.competition.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {competitionEntity.updatedOn ? (
              <TextFormat value={competitionEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.competition.sponsor">Sponsor</Translate>
          </dt>
          <dd>{competitionEntity.sponsor ? competitionEntity.sponsor.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/competition" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/competition/${competitionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CompetitionDetail;
