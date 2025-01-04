import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './sponsor.reducer';

export const SponsorUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const sponsorEntity = useAppSelector(state => state.sponsor.entity);
  const loading = useAppSelector(state => state.sponsor.loading);
  const updating = useAppSelector(state => state.sponsor.updating);
  const updateSuccess = useAppSelector(state => state.sponsor.updateSuccess);

  const handleClose = () => {
    navigate('/sponsor');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...sponsorEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...sponsorEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.sponsor.home.createOrEditLabel" data-cy="SponsorCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.sponsor.home.createOrEditLabel">Create or edit a Sponsor</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="sponsor-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorName')}
                id="sponsor-sponsorName"
                name="sponsorName"
                data-cy="sponsorName"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorDescription')}
                id="sponsor-sponsorDescription"
                name="sponsorDescription"
                data-cy="sponsorDescription"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorBanner1Url')}
                id="sponsor-sponsorBanner1Url"
                name="sponsorBanner1Url"
                data-cy="sponsorBanner1Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorBanner2Url')}
                id="sponsor-sponsorBanner2Url"
                name="sponsorBanner2Url"
                data-cy="sponsorBanner2Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorBanner3Url')}
                id="sponsor-sponsorBanner3Url"
                name="sponsorBanner3Url"
                data-cy="sponsorBanner3Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorExternalUrl')}
                id="sponsor-sponsorExternalUrl"
                name="sponsorExternalUrl"
                data-cy="sponsorExternalUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.sponsorLogoUrl')}
                id="sponsor-sponsorLogoUrl"
                name="sponsorLogoUrl"
                data-cy="sponsorLogoUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.isActive')}
                id="sponsor-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.createdBy')}
                id="sponsor-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.createdOn')}
                id="sponsor-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.updatedBy')}
                id="sponsor-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.sponsor.updatedOn')}
                id="sponsor-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sponsor" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default SponsorUpdate;
