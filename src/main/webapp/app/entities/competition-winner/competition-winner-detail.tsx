import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './competition-winner.reducer';

export const CompetitionWinnerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const competitionWinnerEntity = useAppSelector(state => state.competitionWinner.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="competitionWinnerDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.competitionWinner.detail.title">CompetitionWinner</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.id}</dd>
          <dt>
            <span id="prizeTitle">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.prizeTitle">Prize Title</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.prizeTitle}</dd>
          <dt>
            <span id="citation">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.citation">Citation</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.citation}</dd>
          <dt>
            <span id="certificateUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.certificateUrl">Certificate Url</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.certificateUrl}</dd>
          <dt>
            <span id="selectedBy">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.selectedBy">Selected By</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.selectedBy}</dd>
          <dt>
            <span id="selectionReason">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.selectionReason">Selection Reason</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.selectionReason}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {competitionWinnerEntity.createdOn ? (
              <TextFormat value={competitionWinnerEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{competitionWinnerEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.competitionWinner.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {competitionWinnerEntity.updatedOn ? (
              <TextFormat value={competitionWinnerEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.winningPost">Winning Post</Translate>
          </dt>
          <dd>{competitionWinnerEntity.winningPost ? competitionWinnerEntity.winningPost.id : ''}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.paymentToWinner">Payment To Winner</Translate>
          </dt>
          <dd>{competitionWinnerEntity.paymentToWinner ? competitionWinnerEntity.paymentToWinner.id : ''}</dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.competitionPrize">Competition Prize</Translate>
          </dt>
          <dd>{competitionWinnerEntity.competitionPrize ? competitionWinnerEntity.competitionPrize.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/competition-winner" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/competition-winner/${competitionWinnerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CompetitionWinnerDetail;
